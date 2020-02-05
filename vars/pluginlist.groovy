def call(){
sh "curl -O http://3.15.229.74:8080/jnlpJars/jenkins-cli.jar"
sh "java -jar jenkins-cli.jar -s http://3.15.229.74:8080 groovy --username "admin" --password "11cfd35185f955e0f4f547669ee81ebc7a" = < plugin.groovy > plugins.txt"
}
