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
               withSonarQubeEnv(credentialsId: 'sonar-api'){
                     sh 'mvn clean package sonar:sonar'
            }
          }
      }
    }

    stage('Quality Gate status'){
        steps{
            script{
                waitForQualityGate abortPipeline: false, credentialsId: 'sonar-api'
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
                 credentialsId: 'nexus-auth',
                 groupId: 'tn.esprit.rh',
                 nexusUrl: '192.168.56.111:8081',
                 nexusVersion: 'nexus3',
                 protocol: 'http',
                 repository: 'demoapp-release',
                 version: '1.0'
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