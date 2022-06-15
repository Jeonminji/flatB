pipeline{
  agent any
  
  stages{
    stage('build'){
      steps{
        sh 'cd ./Front/flatb_front && npm install && npm install -save react-scripts && npm run build'      
      }
    }

  }
}
