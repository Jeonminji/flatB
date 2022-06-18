pipeline{
  agent any
  environment{
    registry="sksznddl1/flatb-front"
    registryCredential = 'sksznddl1'
  }
  
  stages{
    stage('build'){
      steps{
          sh 'docker build -f Dockerfile -t $registry:latest .'
      }
    }
   
  }
}
