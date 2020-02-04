 def call(jobname,BUILD_NUMBER)
 {
 sh "curl --silent -u username:admin_admin http://3.15.229.74:8080/job/'${JOB_NAME}'/'${BUILD_NUMBER}'/api/json" 

 }
