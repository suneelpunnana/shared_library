 def call(jobname)
 {
 curl --silent http://admin:TokenID@Jenkins-BuildURL/job/${jobname}/${BUILD_NUMBER}/api/json
