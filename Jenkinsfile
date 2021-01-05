pipeline {
	agent any
	stages {
		stage ('Build API'){
			steps {
				sh 'mvn clean package -DskipTests=true'
			}
		}
		stage ('Execute Unit Tests'){
			steps {
				sh 'mvn test'
			}
		}
		stage ('Sonar Scanner'){
			environment {
				scannerHome = tool 'SONAR_SCANNER'
			}
			steps {
				withSonarQubeEnv('SONAR_LOCAL'){
					sh "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=CloudParkingAPI -Dsonar.host.url=http://sonarqube:9000 -Dsonar.login=edc9bd6eb9515496f214450e8f2657e436571a65 -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**,**/src/test/**,**/model/**,**Application.java "
				}
			}
		}
		stage ('Quality Gate'){
			steps {
			    sleep(10)
				timeout(time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
			}
		}
	}
}

