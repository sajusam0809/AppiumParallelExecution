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
            // ✅ Publish Extent HTML report (with styles & screenshots)
            publishHTML(target: [
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'test-output',
                reportFiles: 'ExtentReport.html',
                reportName: 'Extent Test Report'
            ])
        }
    }
}
