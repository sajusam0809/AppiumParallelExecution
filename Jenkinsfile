pipeline {
    agent any

    tools {
        maven 'Maven_3'
        jdk 'JDK_22'
    }

    environment {
        // Set npm global bin to PATH (for appium, node, etc.)
        PATH = "C:\\Users\\JenkinsUser\\AppData\\Roaming\\npm;%PATH%"
    }

    stages {
        stage('Checkout Code') {
            steps {
                git url: 'https://github.com/sajusam0809/AppiumParallelExecution.git', branch: 'main'
            }
        }

        stage('Setup Appium') {
            steps {
                bat '''
                echo Installing Appium and UiAutomator2 driver...
                npm install -g appium
                appium driver install uiautomator2
                appium driver list --installed
                '''
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

            // Allure Report
            allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
        }
    }
}
