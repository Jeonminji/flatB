pipeline{
  agent any
  environment{
    registry="sksznddl1/flatb-front"
    registryCredential = 'sksznddl1'
  }
  
  stages{
    stage('build'){
      steps{
          sh 'docker build -f Dockerfile-front -t $registry:latest .'
      }
    }
    
  stage('Push image') {
            steps {
                withDockerRegistry([ credentialsId: registryCredential, url: "" ]) {
                    sh 'docker push $registry:latest'
                }
                echo 'Push image...'
            }
        }
   
  }
}

