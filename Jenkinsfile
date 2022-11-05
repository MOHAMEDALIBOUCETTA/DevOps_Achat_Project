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


    stage('Post Build Steps') {
      steps {
        writeFile(file: 'status.txt', text: 'Hey it worked!!!')
      }
    }

  }
}