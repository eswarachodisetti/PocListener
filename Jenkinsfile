pipeline {
  options {
    disableConcurrentBuilds()
  }
  agent {
    label "jenkins-maven"
  }
  environment {
    DEPLOY_NAMESPACE = "default"
    VERSION = "1.0.0-$BUILD_NUMBER"
  }
  stages {
  
     stage('Build') {
      steps {
        container('maven') {
         dir('poclistener') {
		 sh 'ls -lart && mvn -B clean deploy'
		 sh 'chmod u+x *.sh && ./nexus.sh'
		 sh 'mv *.jar ../'
		 //sleep 120
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
	
	 stage('Push Docker') {
		steps{
			script {
				container('jx-base') {
				
					sh 'mount -o remount,rw /home/jenkins/.docker'
					sh 'scp ${WORKSPACE}/config.json /home/jenkins/.docker/'
					sh 'docker push dhanapodigiri/poclistener:$VERSION'	
				}
			
			}
		}
	}
   
    
   
  }
}
