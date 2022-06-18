pipeline{
  agent any
  
  stages{
    stage('build'){
      steps{
        test=docker.build("sksznddl1/flatb-front:${env.BUILD_ID}")
      }
    }

  }
}
