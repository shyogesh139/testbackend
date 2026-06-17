pipeline {
    agent any

    stages {

        stage('Verify') {
            steps {
                sh 'java -version'
                sh 'mvn -version'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                mkdir -p /opt/backend

                cp target/*.jar /opt/backend/application.jar

                pkill -f "application.jar" || true

                nohup java -jar /opt/backend/application.jar \
                --spring.profiles.active=dev \
                --server.port=8081 \
                > /opt/backend/app.log 2>&1 &
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