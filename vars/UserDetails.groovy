import groovy.json.*
import groovy.json.JsonOutput

	

def call(JSON,IP)
{
def jsonString = JSON
def jsonObj = readJSON text: jsonString
def mailcount = jsonObj.config.emails.email.size()

sh "curl  -X GET  -u rig:rigaDapt@devOps '${IP}/rest/api/latest/result/LAT-WEB.json?max-result=50&expand=results.result.artifacts&expand=changes.change.files&start-index=0' -o output.json"
	def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/output.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)


 


  List<String> USERS = new ArrayList<String>()
	List<String> USERF = new ArrayList<String>()
 List<String>  LISTSUCCESS=new ArrayList<String>()
	 List<String>  LISS=new ArrayList<String>()
	 List<String>  LISF=new ArrayList<String>()
	List<String> LISTFAILURE=new ArrayList<String>()
	List<String> SUCCESS = new ArrayList<String>()
    List<String> FAILURE = new ArrayList<String>()
	


 


	def jsonBuilder = new groovy.json.JsonBuilder()

   for(j=0;j<mailcount;j++)
   {
	   def cns=0
	   def cnf=0
    def email=jsonObj.config.emails.email[j] 
  for(i=0;i<50;i++)
  {
 
   
   def state=resultJson.results.result[i].buildState
  
   if(resultJson.results.result[i].buildReason.contains(email) && state.equals("Successful"))
   {
   
    USERS.add(resultJson.results.result[i])
	  
   }
   else if(resultJson.results.result[i].buildReason.contains(email) && state.equals("Failed"))
   {
	   
	   USERF.add(resultJson.results.result[i])
   }
   }
   cns=USERS.size()

	
	   LISS[j]=USERS.clone()
	   LISF[j]=USERF.clone()
	   
   LISTSUCCESS.add(["email":email,"success":LISS[j],"Success_cnt":cns])
   USERS.clear()
	 
   cnf=USERF.size()
   LISTFAILURE.add(["email":email,"failure":LISF[j],"Failure_cnt":cnf])
   USERF.clear()
   }
	for(i=0;i<50;i++)
  {
   def date=resultJson.results.result[i].buildCompletedDate
   def state=resultJson.results.result[i].buildState

   
  if(state.equals("Successful"))
  {
   
 
   SUCCESS.add(resultJson.results.result[i])
     
  }
   else if(state.equals("Failed"))
   {
    
       FAILURE.add(resultJson.results.result[i])
     
   }
  }
	
		    jsonBuilder.Bamboo(
  "teamsuccess" : SUCCESS,
  "teamsuccessbuild_cnt" : SUCCESS.size(),
  "teamfailure" : FAILURE,
  "teamfailurebuild_cnt" :FAILURE.size(),
  "individualsuccess": LISTSUCCESS,
  "individualfailure": LISTFAILURE
  )
	
File file = new File("/var/lib/jenkins/workspace/${JOB_NAME}/bamboo.json")
file.write(jsonBuilder.toPrettyString())
	//def reader1 = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/bamboo.json"),"UTF-8"))
//def resu = jsonSlurper.parse(reader1)

	//println(resu.individualsuccess[2].Success_cnt)
				   //println(jsonBuilder)
	

}
