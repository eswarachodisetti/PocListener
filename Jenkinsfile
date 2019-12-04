pipeline {
  options {
    disableConcurrentBuilds()
  }
  agent {
    label "jenkins-maven"
  }
  environment {
    DEPLOY_NAMESPACE = "jx-staging"
    VERSION = "1.0.0-$BUILD_NUMBER"
    GROUP_ID = "com/poclistener"
    ARTIFACT_ID = "poclistener"
  }
  stages {
  
     stage('Build') {
      steps {
        container('maven') {
         dir('poclistener') {
		// sh 'ls -lart && mvn -B clean deploy'
		 sh 'chmod u+x *.sh && ./nexus.sh $GROUP_ID $ARTIFACT_ID'
		// sh 'mv *.jar ../'
		// sleep 100
			}
        }
      }
    }
/*
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
				container('maven') {
				
					sh 'mount -o remount,rw /home/jenkins/.docker'
					sh 'scp ${WORKSPACE}/config.json /home/jenkins/.docker/'
					sh 'docker push dhanapodigiri/poclistener:$VERSION'	
				}
			
			}
		}
	}
	
	 stage('Deployment') {
      steps {
        container('maven') {
          dir('poclistener') {
        //    sh 'jx step helm apply --name poclistener'
				sh 'jx step helm apply poclistener --name poclistener --namespace=jx-production'
				}
			}
		}
		}
*/
   
    
   
  }
}
