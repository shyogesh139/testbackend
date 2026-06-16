pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Verify') {
            steps {
                sh 'java -version'
                sh 'mvn -version'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Run') {
            steps {
                sh '''
                pkill -f "java -jar" || true

                nohup java -jar target/*.jar \
                --spring.profiles.active=dev \
                --server.port=8081 \
                > app.log 2>&1 &
                '''
            }
        }
    }

    post {
        success {
            echo 'Build Success'
        }

        failure {
            echo 'Build Failed'
        }
    }
}