import groovy.json.*

def call(jsondata,bitbucket){
def jsonString = jsondata
def jsonObj = readJSON text: jsonString
int ecount = jsonObj.riglet_info.auth_users.size()
	def team=jsonObj.riglet_info.name
List<String> jsonStringa= new ArrayList<String>();
  jsonStringa.add(bitbucket)
   //jsonStringa.add(jenkins)
	//jsonStringa.add(sonar)
   List<String> LIST = new ArrayList<String>();
	List<String> JSON = new ArrayList<String>();
	for(k=0;k<ecount;k++)
	{
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
	     println("bhavay")
	    if(a==a){
		     println("bhavay")
	    if(jsonObj.riglet_info.auth_users[k]==jsonObja.bitbucket.Individual_commits.Email){
int total=jsonObja.bitbucket.Individual_commits.Commit_count
 // println(jsonObja)
  println(total)
 println("bhavay")
		    LIST.add(["toolName":name,"metric":"commits","value":total])
		    println(LIST)}}
      }
     }
		JSON.add(["team_member_name":jsonObj.riglet_info.auth_users[k],"teamname":team,"metrics" : LIST])
	}
	
def jsonBuilder = new groovy.json.JsonBuilder()
jsonBuilder(
 "team_members":JSON
  
) 
  
  File file = new File("/var/lib/jenkins/workspace/${JOB_NAME}/Indivscore.json")
file.write(jsonBuilder.toPrettyString())	
}
