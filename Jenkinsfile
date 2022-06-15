pipeline{
  agent any
  
  stages{
    stage('build'){
      steps{
        sh 'cd ./Front/flatb_front && rm -rf node_modules && npm install && npm run build'      
      }
    }

  }
}
