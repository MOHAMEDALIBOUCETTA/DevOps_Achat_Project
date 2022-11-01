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

    stage('Clean'){
                steps {

                sh "mvn clean"
                   }

            }
            stage('Compile'){
                        steps {

                        sh "mvn compile"
                           }

                    }
                    stage("Build") {
                          steps {
                            sh 'mvn -v'
                          }

    stage('Post Build Steps') {
      steps {
        writeFile(file: 'status.txt', text: 'Hey it worked!!!')
      }
    }

  }
}