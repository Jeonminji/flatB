pipeline{
  agent any
  environment{
    registry="sksznddl1/flatb-front"
    registryCredential = 'sksznddl1'
  }
  
  stages{
    stage('build'){
      steps{
        test=docker.build("sksznddl1/flatb-front:latest")
      }
    }

  }
}
