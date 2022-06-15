pipeline{
  agent any
  
  stages{
    stage('cd'){
      steps{
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
