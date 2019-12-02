pipeline {
  options {
    disableConcurrentBuilds()
  }
  agent {
    label "jenkins-maven"
  }
  environment {
    DEPLOY_NAMESPACE = "default"
  }
  stages {
     stage('Build') {
      steps {
        container('maven') {
         dir('TestService') {
		// sh 'rm -rf *'
		 //checkout scm
		 
		// git branch: 'master', url: 'git@github.com:eswarachodisetti/TestService.git'
		// sh 'export GOOGLE_APPLICATION_CREDENTIALS=/home/jenkins/.gcp/.dockerconfigjson'
		// sh 'cd /home/jenkins/ && ls -lart'
		// sh 'cd TestService && ls -lart && mvn clean deploy'
		
		// sh 'mvn dependency:get -DremoteRepositories=http://nexus.jx.35.229.61.119.nip.io/repository/maven-snapshots -DgroupId=com.TestWebservice -DartifactId=TestWebservice -Dversion=0.0.1-SNAPSHOT -Dpackaging=war -Dtransitive=false'
		 sh 'cd TestService && ls -lart && mvn -B release:clean release:prepare release:perform'
		 sleep 120
			}
        }

      }
    }
    
   
  }
}
