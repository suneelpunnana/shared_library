def call(){
sh "curl --globoff -user admin:11cfd35185f955e0f4f547669ee81ebc7a http://3.15.229.74:8080/view/All /api/json?tree=jobs[name,builds[number,actions[parameters[name,value]]]]&pretty=true"
}
