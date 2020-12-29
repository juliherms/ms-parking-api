pipeline {

	agent any
	stages {
		stage ('Build API'){
			steps {
				sh 'mvn clean package -DskipTests=true'
			}
		}
	}

}