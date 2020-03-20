import groovy.json.*
import groovy.json.JsonSlurper 

def call(jsondata,rig){
      def jsonString = jsondata
      def jsonObj = readJSON text: jsonString
      
      int ecnt = jsonObj.config.emails.email.size()
         println("No of users "+ ecnt)
      String a=jsonObj.scm.repositories.repository.repo_name
String repoName=a.replaceAll("\\[", "").replaceAll("\\]","");
	def jsonObja = readJSON text: rig
  
	def IP=jsonObja.url
	def user=jsonObja.userName
	def pass=jsonObja.password

 println(repoName)
     
def responce=	sh(script: "curl -X GET    -u $user:$pass ${IP}/repos/${user}/${repoName}/commits -o commits.json",returnStdout: true)
   try
   {
   def jsonSlurper = new JsonSlurper()
 def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/commits.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def totalcommits = resultJson.size()
      println(totalcommits)
	println(ecnt)
      println(JsonOutput.toJson(resultJson))
      List<String> JSON = new ArrayList<String>();
   	 List<String> COMMIT = new ArrayList<String>();
	 List<String> LIST = new ArrayList<String>();

	 def jsonBuilder = new groovy.json.JsonBuilder()
for(i=0;i<ecnt;i++)
 {
	def email=jsonObj.config.emails.email[i] 
  for(j=0;j<totalcommits;j++)
  {
	 // println(jsonObj.config.emails.email[i])
	 // println(resultJson[j].commit.author.email)
   if(email==resultJson[j].commit.author.email)
   {
	   JSON.add(resultJson[j])
     }
     }
	// println(jsonObj.config.emails.email[i])
	 cnt=JSON.size()
	 LIST[i]=JSON.clone()
	 COMMIT.add(["User_email":email,"User_Commits": LIST[i],"User_Commits_count":cnt])
	//LIST.add(["email":email,"Commit":JSON,"Commit_cnt":cnt])
	 //JCOPY[i]=(JsonOutput.toJson(JSON))
	// println(JCOPY[i])
	 JSON.clear()
	 
	   
     }
 /* for(i=0;i<JCOPY.size();i++)
	{
		println(JCOPY[i])
	}
    */
 jsonBuilder.GITHUB(
  "total_commits" : resultJson,
  "commits_count" : resultJson.size(),
	 "individual_commit_Details":COMMIT
  
  )
File file = new File("/var/lib/jenkins/workspace/${JOB_NAME}/output.json")
	file.write(jsonBuilder.toPrettyString())
//return jsonBuilder
}
catch(Exception e)
{
	e.printStackTrace()
	
}
	 finally{
		
		if(response.contains("200"))
		println("data collected scuccesslfully")	
	if(response.contains("404"))
	println("Not found")
	if(response.contains("400"))
	println("Bad Request")
        if(response.contains("401"))
	println("Unauthorized")
	if(response.contains("403"))
		println("Forbidden")
	if(response.contains("500"))
		println("Internal Server Error")
		 }
}
