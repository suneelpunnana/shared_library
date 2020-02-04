






def call(dsl,jobname){

static url='http://18.221.47.136:8080/createItem?name='+jobname

sh 'curl -O https://repo.jenkins-ci.org/public/org/jenkins-ci/plugins/job-dsl-core/1.76/job-dsl-core-1.76-standalone.jar'

sh "java -jar job-dsl-core-1.76-standalone.jar '${dsl}'"

url='http://18.221.47.136:8080/createItem?name='+jobname
sh "echo ${url}"
sh "curl -s -XPOST  '$url' -u admin:119767fb81f22e2f10d8594e4201717e53 --data-binary @Test.xml -H Content-Type:text/xml"
}
