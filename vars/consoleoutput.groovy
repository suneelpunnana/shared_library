def call()
{
  
sh "curl -XGET http://3.15.229.74:8080/job/jersey/lastBuild/consoleText -u admin:11cfd35185f955e0f4f547669ee81ebc7a"  
 // sh "curl -X GET http://3.15.229.74:8080/job/jersey/lastBuild/consoleText -u admin:11cfd35185f955e0f4f547669ee81ebc7a -O http://3.15.229.74:8080/job/jersey/lastBuild/execution/node/3/ws/console.txtconsole.txt "
//sh "wget --user=admin --password='11cfd35185f955e0f4f547669ee81ebc7a' http://3.15.229.74:8080/job/jersey/lastBuild/consoleText"  
//sh "curl -s  http://3.15.229.74:8080/job/jersey/lastBuild/consoleText -u admin:11cfd35185f955e0f4f547669ee81ebc7a -o console.txt"
}
