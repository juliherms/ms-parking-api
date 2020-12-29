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
	}

}