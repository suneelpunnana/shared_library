






def call(dsl,jobname){

static url='http://3.15.229.74:8080/createItem?name='+jobname

sh 'curl -O https://repo.jenkins-ci.org/public/org/jenkins-ci/plugins/job-dsl-core/1.76/job-dsl-core-1.76-standalone.jar'

sh "java -jar job-dsl-core-1.76-standalone.jar '${dsl}'"

url='http://3.15.229.74:8080/createItem?name='+jobname
sh "echo ${url}"
sh "curl -s -XPOST  '$url' -u admin:11cfd35185f955e0f4f547669ee81ebc7a --data-binary @Test.xml -H Content-Type:text/xml"
}
