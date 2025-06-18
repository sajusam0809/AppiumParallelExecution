pipeline {
    agent any

    tools {
        maven 'Maven_3'
        jdk 'JDK_22'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git url: 'https://github.com/sajusam0809/AppiumParallelExecution.git', branch: 'main'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn clean test -DsuiteXmlFile=testng.xml'
            }
        }
    }

   post {
       always {
           archiveArtifacts artifacts: 'test-output/**/*.*', fingerprint: true

           publishHTML(target: [
               allowMissing: false,
               alwaysLinkToLastBuild: true,
               keepAll: true,
               reportDir: 'test-output',
               reportFiles: 'ExtentReport.html',
               reportName: 'Extent Test Report'
           ])

           // ✅ Publish Allure Report (requires Allure plugin installed in Jenkins)
                       allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
       }
   }