pipeline {
    agent any
    stages{
        stage('Build Back-End'){
            steps{
                bat 'mvn clean package -DskipTests=true'
            }
        }
        stage('Unit Test') {
           steps {
               bat 'mvn test'
           }
        }
    }
}