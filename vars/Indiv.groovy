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
	List<String> JSON1 = new ArrayList<String>();
	for(k=0;k<ecount;k++)
	{
		def email=jsonObj.riglet_info.auth_users[k]
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
	    
	    def resemail=jsonObja.bitbucket.Individual_commits[k].Email
	    if(email==resemail){
int total=jsonObja.bitbucket.Individual_commits.Commit_count
 // println(jsonObja)
  println(total)
 println("bhavay")
		    LIST.add(["toolName":name,"metric":"commits","value":total])
		    println(LIST)}
      }
     }
		JSON1[k]=LIST.clone()
		JSON.add(["team_member_name":email,"teamname":team,"metrics" : JSON[k]])
		LIST.clear()
	}
	
def jsonBuilder = new groovy.json.JsonBuilder()
jsonBuilder(
 JSON
  
) 
  
  File file = new File("/var/lib/jenkins/workspace/${JOB_NAME}/Indivscore.json")
file.write(jsonBuilder.toPrettyString())	
}
