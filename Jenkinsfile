pipeline{
  agent any
  
  stages{
    stage('build'){
      steps{
        sh 'cd ./Front/flatb_front && npm i -g npm && rm -rf node_modules && npm cache clean && npm install && npm install react-scripts && npm run build'      
      }
    }

  }
}
