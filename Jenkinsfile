def  appName = 'gceme'
def  feSvcName = "${appName}-frontend"

pipeline {
  agent {
    kubernetes {
      label 'sample-app'
      defaultContainer 'jnlp'
      yaml """
apiVersion: v1
kind: Pod
metadata:
labels:
  component: ci
spec:
  # Use service account that can deploy to all namespaces
  serviceAccountName: cd-jenkins
  containers:
  - name: maven
    image: maven:3.5.2-jdk-8
    command:
    - cat
    tty: true
  - name: gcloud
    image: gcr.io/cloud-builders/gcloud
    command:
    - cat
    tty: true
  - name: kubectl
    image: gcr.io/cloud-builders/kubectl
    command:
    - cat
    tty: true
"""
}
  }
  stages {
    stage('Test') {
      steps {
        container('golang') {
          sh """
            echo "testtttttttt"
          """
        }
      }
    }
    stage('Build and push image with Container Builder') {
      steps {
        container('gcloud') {
          sh ""
        }
      }
    }
    stage('Deploy Canary') {
       
      steps {
        container('kubectl') {
          // Change deployed image in canary to the one we just built
          sh("kubectl get pods")
        } 
      }
    }
   
 
  }
}