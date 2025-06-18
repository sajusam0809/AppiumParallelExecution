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

           // Send email with report attached
           emailext (
               subject: "Jenkins Build #${env.BUILD_NUMBER} - Test Report",
               body: """<p>Hi Team,</p>
                        <p>The build #${env.BUILD_NUMBER} has completed.</p>
                        <p>Check the test report attached or view it in Jenkins:</p>
                        <p><a href="${env.BUILD_URL}Extent_20Test_20Report/">Extent Test Report</a></p>""",
               to: "sajusam0809@gmail.com",
               attachmentsPattern: 'test-output/ExtentReport.html',
               mimeType: 'text/html'
           )
       }
   }