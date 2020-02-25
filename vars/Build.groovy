import groovy.json.*

@NonCPS
    
    def call()
{
    sh "curl -XGET -g http://52.14.229.175:8080/job/normalpro/api/json?tree=builds[number,status,timestamp,id,result] -u suneel:11035ac86f58bc32d03d8e873b7cc063a3 -o Build.json"
    create()
}

create(){
def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/normalpro/Build.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def size = resultJson.builds[0].number
   
    println("total number of builds:"+resultJson.builds[0].number)
   def countS=0;
   def countF=0;
   for(int i=0;i<size;i++){
      if(resultJson.builds[i].result=="SUCCESS"){
         countS++;
      }
      else{
         countF++;
         
      }
      
   }
    println("total number of successfull builds:"+countS)
    println("total number of FAILURE builds:"+countF)
     
   
}
