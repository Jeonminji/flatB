pipeline{
  agent any
  environment{
    registry="sksznddl1/flatb-front"
    registryCredential = 'sksznddl1'
  }
  
  stages{
    stage('build'){
      steps{
          sh 'docker build -f Dockerfile-front -t $registry:$env.BUILD_ID .'
      }
    }
    
  stage('Push image') {
            steps {
                withDockerRegistry([ credentialsId: registryCredential, url: "" ]) {
                    sh 'docker push $registry:$env_BUILD.ID'
                }
                echo 'Push image...'
            }
        }
   
  }
}

