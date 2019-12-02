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
         dir('poclistener') {
		 sh 'ls -lart && mvn -B clean deploy'
		 sh 'chmod u+x *.sh && ./nexus.sh'
		 sh 'mv *.jar ../'
		 sleep 120
			}
        }
      }
    }

	stage('Build Docker') {
      steps {
        container('maven') {
          sh 'docker build -t dhanapodigiri/poclistener:$VERSION .'
		      sh 'docker images'
	
        }

      }
    }
    
   
  }
}
