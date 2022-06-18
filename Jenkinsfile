pipeline{
  agent any
  environment {
    registry="sksznddl1/flatb-front"
    registryCredential = 'sksznddl1'
    
    backRegistry="yih0322/flatbback"
    backCredential = 'yih0322'
  }
  
  stages{
    stage('build'){
      steps {
          sh 'docker build -f Dockerfile-front -t $registry:$BUILD_ID .'
          sh 'docker build -t $backRegistry:latest .'
      }
     }
    
    stage('Push Front image') {
      steps {
        withDockerRegistry([ credentialsId: registryCredential, url: "" ]) {
          sh 'docker push $registry:$BUILD_ID'
        }
         echo 'Push Front image...'
      }
     }
 
    stage('Push Back image') {
      steps {
        withDockerRegistry([ credentialsId: backCredential, url: "" ]) {
          sh 'docker push $backRegistry:latest'
         }
         echo 'Push Back image...'
      }
    }
  }
}
