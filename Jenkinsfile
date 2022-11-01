pipeline {
  agent any
  stages {
    stage('log versions') {
      parallel {
        stage('log versions') {
          steps {
            sh '''git --version
mvn --version
java -version
ant -version'''
          }
        }

        stage('Maven POM Check') {
          steps {
            fileExists 'pom.xml'
          }
        }

      }
    }

    stage('Build App') {
      steps {
        pwd()
        sh 'mvn clean install'
      }
    }

    stage('Finalize') {
      steps {
        writeFile(file: 'complete.txt', text: 'We completed it!')
        timeout(time: 200) {
          archiveArtifacts '*.*'
        }

      }
    }

  }
}