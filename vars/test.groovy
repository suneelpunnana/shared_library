import groovy.json.*
import java.io.File
@NonCPS
create(){
def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/normalpro/username.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
    int countS=0;
    int countv=0;
 int success_sunil=0;
 int fail_sunil=0;
 int success_vicky=0;
 int fail_vicky=0;
 int countN=0;
   def build=resultJson.users[0].project.builds[0].actions[2].buildsByBranchName.master.buildNumber
 
  println("total number of builds"+build)

   for(int i=0;i<build;i++){
      if(resultJson.users[0].project.builds[i].actions[0].causes[0].userName=="suneelpunnana"){
         countS++;
          if(resultJson.users[0].project.builds[i].result=="SUCCESS"){
           success_sunil++;
            }else{
           fail_sunil++;
            }
       }else{
       countv++;
       if(resultJson.users[0].project.builds[i].result=="SUCCESS"){
        success_vicky++;
         }else{
        fail_vicky++;
         }
      }
    
   }
    println("total number of builds by suneel:"+countS) 
    println("total number of success builds by suneel:"+success_sunil) 
    println("total number of failed builds by suneel:"+fail_sunil) 
     println("total number of builds by vicky:"+countv) 
    println("total number of success builds by vicky:"+success_vicky) 
     println("total number of failed builds by vicky:"+fail_vicky) 
	writeFile file: 'user.json', text: "{ \
	\"name\": \"Suneel\"\n, \
	\"Builds\": \"${countS}\", \
	\"Successful\": \"${success_sunil}\", \
	\"Failed\": \"${fail_sunil}\" \
}"

	
}
     

def call()
{
    sh "curl -XGET -g http://52.14.229.175:8080/asynchPeople/api/json?depth=3 -o username.json"
	sh 'echo "{" >> user.json' 
	
    create()
	
	/*sh 'echo """{ \
	\"name\": \"Suneel\"\n, \
	\"Builds\": \"${countS}\", \
	\"Successful\": \"'${success_sunil}'\", \
	\"Failed\": \"'${fail_sunil}'\" \
}""" > suneel.json'*/
	
	 
}
