

pipeline {
    agent any  // This assigns an agent to run the pipeline

    stages {
        stage('Checkout') {
            steps {
                // Clones the code from Git
                git branch: 'main', url: 'https://github.com/Assil2/https://github.com/Assil2/5bi2_grp7_Projet_Devops.git'
            }
        }

        stage('Build') {
            steps {
                // Executes Maven build commands
                sh 'mvn clean install'
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
