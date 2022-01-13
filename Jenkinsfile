pipeline {
    agent any

    environment {
        REMOTE_ADDRESS = "localhost:8080"
    }

    stages {
        stage ('Test & Build Artifact') {
            agent {
                docker {
                    image 'thyrlian/android-sdk'
                    args '-v "$PWD":/app'
                    reuseNode true
                }
            }
            steps {
                sh 'chmod +x gradlew'
                sh '././gradlew build --no-daemon'
            }
        }
    }
}