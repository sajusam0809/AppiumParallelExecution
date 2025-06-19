pipeline {
    agent any

    tools {
        maven 'Maven_3'
        jdk 'JDK_22'
    }

    environment {
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
                echo Setting up PATH...
                set PATH=C:\\Users\\JenkinsUser\\AppData\\Roaming\\npm;%PATH%

                echo Installing Appium...
                npm install -g appium

                echo Installing UiAutomator2...
                appium driver install uiautomator2

                echo Installed drivers:
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

            allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
        }
    }
}
