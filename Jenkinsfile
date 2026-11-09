pipeline {
    agent any
    stages {
        stage('Checkout') { steps { checkout scm } }
        stage('Build and Test') { steps { sh 'mvn clean test' } }
        stage('Package') { steps { sh 'mvn package -DskipTests' } }
    }
    post {
        always {
            junit 'target/surefire-reports/*.xml'
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
    }
}