 def call(jobname,BUILD_NUMBER)
 {
  sh curl -s http://3.15.229.74/:8080/job/'${jobname}'/'$(BUILD_NUMBER)'/buildstatus/api/json --user admin:11cfd35185f955e0f4f547669ee81ebc7a"
   }
