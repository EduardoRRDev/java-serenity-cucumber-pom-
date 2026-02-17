pipeline {
    agent any

    tools {
        jdk 'jdk17'
    }

    environment {
        SONAR_HOST_URL = 'https://sonarcloud.io'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh './gradlew clean compileJava compileTestJava --no-daemon'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew test --no-daemon'
            }
        }

        stage('SonarCloud Analysis') {
            when {
                expression { env.SONAR_TOKEN != null && env.SONAR_TOKEN != '' }
            }
            steps {
                sh "./gradlew sonarqube -Dsonar.token=${env.SONAR_TOKEN} --no-daemon"
            }
        }
    }

    post {
        always {
            publishHTML([
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target/site/serenity',
                reportFiles: 'index.html',
                reportName: 'Serenity Report',
                reportTitles: ''
            ])
        }
    }
}
