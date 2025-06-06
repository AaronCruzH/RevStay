pipeline {
    agent any

    environment {
        EC2_USER = 'ec2-user'
        EC2_HOST = '3.85.92.181'
        PRIVATE_KEY = credentials('jenkins-ec2-key')
        JAR_NAME = 'stay-0.0.1-SNAPSHOT.jar'
        IMAGE_NAME = 'revstay-auto'
        CONTAINER_NAME = 'revstay-auto-container'
    }

    stages {
        stage('Clone Repo') {
            steps {
                git branch: 'main', url: 'https://github.com/AaronCruzH/RevStay'
            }
        }

        stage('Build JAR with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Save Docker Image & SCP to EC2') {
            steps {
                sh '''
                docker save $IMAGE_NAME | bzip2 > image.tar.bz2
                scp -i $PRIVATE_KEY image.tar.bz2 $EC2_USER@$EC2_HOST:~/
                '''
            }
        }

        stage('Deploy to EC2') {
            steps {
                sh '''
                ssh -i $PRIVATE_KEY $EC2_USER@$EC2_HOST 'bash -s' <<'ENDSSH'
                    docker stop $CONTAINER_NAME || true
                    docker rm $CONTAINER_NAME || true
                    bunzip2 -f image.tar.bz2
                    docker load < image.tar
                    docker run -d -p 8080:8080 --name $CONTAINER_NAME $IMAGE_NAME
                ENDSSH
                '''
            }
        }
    }
}