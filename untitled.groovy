def  appName = 'gceme'
def  feSvcName = "${appName}-frontend"

pipeline {
  agent {
    kubernetes {
      label 'liclient'
      defaultContainer 'jnlp'
      yaml """
apiVersion: v1
kind: Pod
metadata:
labels:
  component: ci
spec:
  serviceAccountName: cd-jenkins
  containers:
  - name: maven
    image: maven:3.5.2-jdk-8
    command:
    - cat
    tty: true
  - name: docker
    image: docker
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
        container('maven') {
          sh """
            mvn test
          """
        }
      }
    }
    stage('Build') {
      steps {
        container('maven') {
          sh "mvn clean install -DskipTests"
        }
      }
    }
    stage('Create Docker images') {
      steps {
        container('docker') {
          sh "mvn clean install -DskipTests"
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