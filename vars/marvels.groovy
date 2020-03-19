import groovy.json.*

def call(jsondata,bitbucket,jenkins,sonar){
def jsonString = jsondata
def jsonObj = readJSON text: jsonString
int ecount = jsonObj.riglet_info.auth_users.size()
	def team=jsonObj.riglet_info.name
List<String> jsonStringa= new ArrayList<String>();
  jsonStringa.add(bitbucket)
   jsonStringa.add(jenkins)
	jsonStringa.add(sonar)
   List<String> LIST = new ArrayList<String>();
   for(i=0;i<jsonStringa.size();i++)
  { 
    int score=0
    String name="  "
	  String metric=" "
    if(jsonStringa[i].contains("bitbucket"))
    {
      name="bitbucket"
	  //  metric="commits"
//def jsonStringa = bitbucket
def jsonObja = readJSON text: jsonStringa[i]
int total=jsonObja.bitbucket.Commit_count
 // println(jsonObja)
  //println(total)
 
	    LIST.add(["toolName":name,"metric":"commits","value":total])
      }
      if(jsonStringa[i].contains("JENKINS"))
    {
      name="jenkins"
      def jsonObjb = readJSON text: jsonStringa[i]
	   // print jsonObjb
      def total=jsonObjb.JENKINS.teambuild_cnt
  def scnt =jsonObjb.JENKINS.teamsuccessbuild_cnt
	    def fcnt=jsonObjb.JENKINS.teamfailurebuild_cnt
	    LIST.add(["toolName":name,"metricName":"total_builds","value":total])
	    LIST.add(["toolName":name,"metricName":"successful_builds","value":scnt])
	    LIST.add(["toolName":name,"metricName":"failure_builds","value":fcnt])
      }
      if(jsonStringa[i].contains("Sonar"))
    {
	    name="sonar"
	    def jsonObjc = readJSON text: jsonStringa[i]
	    //print jsonObjc
	    for(i=0;i<jsonObjc.Sonar.Metrics.component.measures.size();i++){
		    //print jsonObjc.Sonar.Metrics.component.measures
    def sonar_metric=jsonObjc.Sonar.Metrics.component.measures[i].metric
		    def d=jsonObjc.Sonar.Metrics.component.measures[i].value
    double data = Double.parseDouble(d); 
       LIST.add(["toolName":name,"metricName":sonar_metric,"value":data])
	    }
    }
     }
/*def jsonBuilder = new groovy.json.JsonBuilder()

jsonBuilder(
 "teamName":team,
  "metrics" : LIST
  
) 
  
  File file = new File("/var/lib/jenkins/workspace/${JOB_NAME}/Teamscore.json")
file.write(jsonBuilder.toPrettyString())	
}*/

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
  
	       def resemail=jsonObja.bitbucket.Individual_commits[j].Email
	       if(email==resemail)
  {
	  def total=jsonObja.bitbucket.Individual_commits[j].Commit_count
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
   "teamName":team,
  "metrics" : LIST,
	JSON
) 
  
  File file = new File("/var/lib/jenkins/workspace/${JOB_NAME}/Indivscore.json")
file.write(jsonBuilder.toPrettyString())
    
  //println(JSON)
}
