import groovy.json.*
import groovy.json.JsonOutput
def call(jsondata,bitbucket,jenkins)
{
def jsonString = jsondata
def jsonObj = readJSON text: jsonString
int ecount = jsonObj.riglet_info.auth_users.size()
	def team=jsonObj.riglet_info.name

 List<String> jsonStringa= new ArrayList<String>();
	jsonStringa.add(bitbucket)
   jsonStringa.add(jenkins)
 List<String> JSON = new ArrayList<String>();
  List<String> LIST = new ArrayList<String>();
  List<String> JSON1 = new ArrayList<String>();
	
	 for(j=0;j<ecount;j++)
   {
	 def email=jsonObj.riglet_info.auth_users[j]
	   int score=0
    int reward=0
    String name="  "
	 for(i=0;i<jsonStringa.size();i++)
  { 
   if(jsonStringa[i].contains("bitbucket"))
      {
        name="bitbucket"
	      //metric="commits"
        def jsonObja= readJSON text: jsonStringa[i]
  //println(jsonObj)
  def total=jsonObja.bitbucket.Individual_commits[j].Commit_count
	       def resemail=jsonObja.bitbucket.Individual_commits[k].Email
	       if(email==resemail)
  {
    LIST.add(["toolName":name,"metricName":"commits","value":total])
  }
      }
	  
  if(jsonStringa[i].contains("JENKINS"))
    {
 
	   
	    
     name="jenkins"
    //  def jsonStringb = bamboo
	   // def jsonString1 = jsonStringa[i]
	   def jsonObjb = readJSON text: jsonStringa[i]

  //println(jsonObja)
  def scnt =jsonObjb.JENKINS.individualsuccess[j].Success_cnt
  def fcnt =jsonObjb.JENKINS.individualfailure[j].Failure_cnt
 def email1=jsonObjb.JENKINS.individualsuccess[j].email
      
 // def res=bamboo1.bamboo.teamsuccessbuild_cnt
 // def obj = JSON.parse(bamboo1)
 println(scnt)
 //int score=0
	  
 if(email==email1)
  {
   // LIST.add(["toolName":name,"metricName":"total_builds","value":total])
	    
 
	   LIST.add(["toolName":name,"metricName":"successful_builds","value":scnt])
	    LIST.add(["toolName":name,"metricName":"failure_builds","value":fcnt])
  }
   }
	   
	  
    }
	   JSON1[j]=LIST.clone()
	   
   JSON.add(["teamMemberName":email,"teamName":team,"metrics":JSON1[j]])
    LIST.clear()
	//reward=0    
	   
    }
	
     def jsonBuilder = new groovy.json.JsonBuilder()

jsonBuilder(
   JSON
  
) 
  
  File file = new File("/var/lib/jenkins/workspace/${JOB_NAME}/indiv.json")
file.write(jsonBuilder.toPrettyString())
    
  //println(JSON)
}
