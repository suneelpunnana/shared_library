import groovy.json.*
@NonCps
create(){
def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/normalpro/Build.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
    /* def total = resultJson.builds.length
    def suc=0
    def fail=0
    def none=0
    for(int i=0;i<total;i++){
        if(resultJson.builds[i].result==="SUCCESS")
        { suc++
        }
        else if(resultJson.builds[i].result==="FAILURE"){
            fail++
        }else{
            none++
        }
    }*/
    println(resultJson.builds[0].number)
}
    
    def call()
{
    sh "curl -XGET -g http://52.14.229.175:8080/job/normalpro/api/json?tree=builds[number,status,timestamp,id,result] -u suneel:11035ac86f58bc32d03d8e873b7cc063a3 -o Build.json"
    create()
}
