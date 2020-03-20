import groovy.json.*

@NonCPS
create(){
def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/Build.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
println("total number of builds:"+resultJson.builds[0].number)
   int countS=0;
   int countF=0;
   for(int i=0;i<resultJson.builds[0].number;i++){
      if(resultJson.builds[i].result=="SUCCESS"){
         countS++;
      }
      else if(resultJson.builds[i].result=="FAILURE"){
         countF++;
         
      }
      
   }
    println("total number of successfull builds:"+countS)
    println("total number of FAILURE builds:"+countF)
 
     
   
}
    
    def call()
{
   sh "curl -XGET -g http://52.14.229.175:8080/job/${JOB_NAME}/api/json?tree=builds[number,status,timestamp,id,result] -u bhavya:1126482a1b9125e89e2cdeac17df05ddb0 -o Build.json"
    create()
}



