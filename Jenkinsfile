pipeline{
  agent any
  
  stages{
    stage('build'){
      steps{
        sh 'cd ./Front/flatb_front && yarn install && yarn run build'      
      }
    }

  }
}
