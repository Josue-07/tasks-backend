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
        stage('Sonar Analysis'){
        	environment {
	            scannerHome = tool 'SONAR_SCANNER'
	        }
            steps {
            	withSonarQubeEnv('SONAR_LOCAL'){
                	bat "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack-End -Dsonar.host.url=http://localhost:9000 -Dsonar.login=bf5a588695a4f3a0574c80023a61d9988a18cec3 -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**,**/src/test/**,**/model/**,**Application.java"
            	    
            	}

            }
            
        }
        stage('Quality Gate'){
            steps{
            	sleep(10)
                timeout(time: 1, unit: 'MINUTES'){
                    waitForQualityGate abortPipeline: true
                 
                }

            }

            
        }


    }
}
