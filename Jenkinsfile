pipeline {
    agent any

    environment {
        REMOTE_ADDRESS = "localhost:8080"
    }

    stages {
        stage ('Test & Build Artifact') {
            agent {
                docker {
                    image 'openjdk:11'
                    args '-v "$PWD":/app'
                    reuseNode true
                }
            }
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew clean build'
            }
        }
    }
}