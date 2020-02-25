import groovy.json.*

@NonCPS
create(){
def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/normalpro/Build.json"),"UTF-8"))
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
    sh "curl -XGET -g http://52.14.229.175:8080/job/normalpro/api/json?tree=builds[number,status,timestamp,id,result] -u suneel:11035ac86f58bc32d03d8e873b7cc063a3 -o Build.json"
    create()
}


