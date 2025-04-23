pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "eduardoal85/revstay-auto:v1"
        EC2_HOST = "3.85.92.181"
        EC2_USER = "ec2-user"
        SSH_KEY = credentials('ec2-ssh-key')
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh "docker build -t ${DOCKER_IMAGE} ."
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    withDockerRegistry([credentialsId: 'docker-hub-credentials', url: '']) {
                        sh "docker push ${DOCKER_IMAGE}"
                    }
                }
            }
        }

        stage('Deploy to AWS EC2') {
            steps {
                sshagent(['jenkins-ec2-key']) {
                    sh "ssh -o StrictHostKeyChecking=no ${EC2_USER}@${EC2_HOST} \"docker pull ${DOCKER_IMAGE} && docker stop revstay-backend-auto || true && docker rm revstay-backend-auto || true && docker run -d -p 8080:8080 --name revstay-backend-auto ${DOCKER_IMAGE}\""
                }
            }
        }
    }

    post {
        success {
            echo 'Deployment Successful!'
        }
        failure {
            echo 'Deployment Failed!'
        }
    }
}