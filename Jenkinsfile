pipeline{
  agent any
  
  stages{
    stage('build'){
      steps{
        sh 'cd ./Front/flatb_front/'
        sh 'npm install'
        sh 'npm run build'
      }
    }
  }
}
