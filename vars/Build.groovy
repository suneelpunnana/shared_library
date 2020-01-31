def call(jobname)
{
  sh "curl -s http://3.15.229.74:8080/job/${jobname}/lastBuild/api/json -u admin:11cfd35185f955e0f4f547669ee81ebc7a"
}
