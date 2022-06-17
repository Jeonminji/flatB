node {
	def back
	def front
  
	stage('Clone repository') {
		git 'https://github.com/Jeonminji/flatB.git'
	}
  
	stage('Build Back image') {
		back = docker.build("yih0322/flatbback")
	}

	stage('Build Front image') {
		sh 'cd ./Front/flatb_front'
		front = docker.build("yih0322/flatbfront")
	}

	stage('Push Back image') {
		docker.withRegistry('https://registry.hub.docker.com', 'yih0322') {
		back.push("${env.BUILD_NUMBER}")
		back.push("latest")
	}

	stage('Push Front image') {
		docker.withRegistry('https://registry.hub.docker.com', 'yih0322') {
		front.push("${env.BUILD_NUMBER}")
		front.push("latest")
	}
}

