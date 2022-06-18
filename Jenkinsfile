pipeline{
  agent any
  environment {
    registry="sksznddl1/flatb-front"
    registryCredential = 'sksznddl1'
    
    backRegistry="yih0322/flatbback"
    backCredential = 'yih0322'
    
    PROJECT_ID = 'handy-resolver-344207'
    CLUSTER_NAME = 'k8s'
    LOCATION = 'asia-northeast3-a'
    CREDENTIALS_ID = 'gke'

  }
  
  stages{
    stage('build'){
      steps {
          sh 'docker build -f Dockerfile-front -t $registry:$BUILD_ID .'
          sh 'docker build -t $backRegistry:latest .'
      }
     }
    
    stage('Push Front image') {
      steps {
        withDockerRegistry([ credentialsId: registryCredential, url: "" ]) {
          sh 'docker push $registry:$BUILD_ID'
        }
         echo 'Push Front image...'
      }
     }
 
    stage('Push Back image') {
      steps {
        withDockerRegistry([ credentialsId: backCredential, url: "" ]) {
          sh 'docker push $backRegistry:latest'
         }
         echo 'Push Back image...'
      }
    }
    
     stage('Deploy to GKE'){
      steps{
          sh "sed -i 's/$registry/$registry:$BUILD_ID/g' front_deployment.yml"
          step([$class: 'KubernetesEngineBuilder', projectId: env.PROJECT_ID, custerName: env.CLUSTER_NAME, location: env.LOCATION, manifestPattern: 'back_deployment.yml', credentialsId: env.CREDENTIALS_ID,verifyDeployments: true])
          step([$class: 'KubernetesEngineBuilder', projectId: env.PROJECT_ID, custerName: env.CLUSTER_NAME, location: env.LOCATION, manifestPattern: 'front_deployment.yml', credentialsId: env.CREDENTIALS_ID,verifyDeployments: true])
      }
    }

    
    
  }
}
