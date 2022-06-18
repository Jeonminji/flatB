pipeline{
  agent any
  
  stages{
    stage('build'){
      steps{
        sh 'cd ./Front/flatb_front'
        test=docker.build("sksznddl1/flatb-front:${env.BUILD_ID}")
      }
    }

  }
}
