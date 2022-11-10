pipeline {
  agent any
  stages {
    stage('Log Tool Version') {
      parallel {
        stage('Log Tool Version') {
          steps {
            sh '''mvn --version
                  git --version
                  java -version'''
          }
        }

        stage('Check for POM') {
          steps {
            fileExists 'pom.xml'
          }
        }

      }
    }

    stage('Maven Test') {
          steps {
            sh 'mvn test'
          }
        }

    stage('Integration testing'){
        steps {
            sh 'mvn verify -DskipUnitTests'
          }
        }

    stage('Maven Build Project') {
      steps {
        sh 'mvn clean install'
      }
    }

    stage('Maven Compile') {
      steps {
        sh 'mvn compile'
      }
    }

    stage('Static code Analysis'){
      steps{
          script{
          withSonarQubeEnv(credentialsId: 'sonar-apikey') {
              sh 'mvn clean package sonar:sonar'
            }
          }
      }
    }

    stage('Quality Gate status'){
        steps{
            script{
                waitForQualityGate abortPipeline: false, credentialsId: 'sonar-apikey'
            }
        }
    }

    stage('Upload war file to Nexus'){
        steps{
            script{

                nexusArtifactUploader artifacts:
                 [
                    [
                        artifactId: 'achat',
                         classifier: '', file: 'target/Uber.jar',
                          type: 'jar'
                          ]
                 ],
                 credentialsId: 'nexus-auth0',
                 groupId: 'tn.esprit.rh',
                 nexusUrl: '192.168.56.111:8081',
                 nexusVersion: 'nexus3',
                 protocol: 'http',
                 repository: 'demoapp-release',
                 version: '1.0'
            }
        }
    }

    stage('Docker Image Build'){
        steps{
            script{
                sh 'docker build -t dali099/achat-1.0 .'
                //sh 'docker image build -t $JOB_NAME:v1.$BUILD_ID .'
                //sh 'docker build $JOB_NAME:v1.$BUILD_ID dali099/$JOB_NAME:v1.$BUILD_ID'
                //sh 'docker image tag $JOB_NAME:v1.$BUILD_ID dali099/$JOB_NAME:latest'
            }
        }
    }

    stage('Push Image to Docker Hb'){
        steps{
            script{
                withCredentials([string(credentialsId: 'git_creds', variable: 'docker_hub_cred')]) {
                    sh 'docker login -u dali099 -p ${docker_hub_cred}'
                    sh 'docker image push dali099/$JOB_NAME:v1.$BUILD_ID'
                    sh 'docker image push dali099/$JOB_NAME:latest'
                }
            }
        }
    }


    stage('Post Build Steps') {
      steps {
        writeFile(file: 'status.txt', text: 'Hey it worked!!!')
      }
    }


  }
}