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
            	sleep(20)
                timeout(time: 1, unit: 'MINUTES'){
                    waitForQualityGate abortPipeline: true
                 
                }

            }

            
        }
      stage('Deploy BackEnd'){
		steps{
		    deploy adapters: [tomcat8(credentialsId: 'toncat_user_login', path: '', url: 'http://localhost:8001/')], contextPath: 'tasks-backend', war: 'target\\tasks-backend.war'
		}
          
      }
      stage('API Test'){
		steps{
			dir('api-test'){
			git credentialsId: 'github_login', url: 'https://github.com/Josue-07/api-task-automation'	
               bat 'mvn test'
			    
			}

		}
          

      }
      stage('Deploy Front-End'){
		steps{
		dir('front-end'){
			git credentialsId: 'github_login', url: 'https://github.com/Josue-07/tasks-frontend'
			bat 'mvn clean package'
			deploy adapters: [tomcat8(credentialsId: 'toncat_user_login', path: '', url: 'http://localhost:8001/')], contextPath: 'tasks', war: 'target/tasks.war'
	    
		}

		}
          
      }
      stage('Functional Test'){
          steps {
              dir('functional-test'){            
                git credentialsId: 'github_login', url: 'https://github.com/Josue-07/task-functional-test'
                bat 'mvn test'
              }

          }
      }


    }
}
