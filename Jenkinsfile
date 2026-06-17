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
                cp target/*.jar /opt/backend/application.jar
                sudo systemctl restart backend
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