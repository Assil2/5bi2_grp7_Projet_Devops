

pipeline {
    agent any  // This assigns an agent to run the pipeline

    stages {
        stage('Checkout') {
            steps {
                // Clones the code from Git
                git branch: 'main', url: 'https://github.com/username/project-repo.git'
            }
        }

        stage('Build') {
            steps {
                // Executes Maven build commands
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                // Runs Maven tests
                sh 'mvn test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                // Runs SonarQube for code quality checks
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage('Package') {
            steps {
                // Packages the project into a JAR/WAR file
                sh 'mvn package'
            }
        }

        stage('Publish to Nexus') {
            steps {
                // Deploys the packaged project to Nexus Repository
                sh 'mvn deploy'
            }
        }

        stage('Docker Build and Push') {
            steps {
                // Builds Docker image and pushes it to Docker Hub
                sh 'docker build -t username/project:latest .'
                sh 'docker push username/project:latest'
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully.'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}
