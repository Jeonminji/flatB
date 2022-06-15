pipeline{
  agent any
  
  stages{
    stage('cd'){
      steps{
        sh 'cd ./Front/flatb_front/'
        sh 'ls -a'      
      }
    }
    stage('build'){
      steps{
        sh 'npm install'
      }
    }
  }
}
