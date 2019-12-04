pipeline {
  options {
    disableConcurrentBuilds()
  }
  agent {
    label "jenkins-maven"
  }
  environment {
    APPLICATION = "poclistener"
    DEPLOY_NAMESPACE = "jx-production"
    VERSION = "1.0.0-$BUILD_NUMBER"
    GROUP_ID = "com/poclistener"
    ARTIFACT_ID = "poclistener"
    MAVEN_VERSION = "0.0.1-SNAPSHOT"
    EXTENTION = "jar"
	DOCKER_HUB_REPO = "dhanapodigiri"
    
  }
  stages {
  
     stage('Build') {
      steps {
        container('maven') {
         dir("$APPLICATION") {
		 sh 'ls -lart && mvn -B clean deploy'
		 sh 'chmod u+x *.sh && ./nexus.sh $GROUP_ID $ARTIFACT_ID $MAVEN_VERSION $EXTENTION'
		 sh 'mv *.jar ../'
			}
        }
      }
    }

	stage('Build Docker') {
      steps {
        container('maven') {
          sh 'docker build -t $DOCKER_HUB_REPO/$APPLICATION:$VERSION .'
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
					sh 'docker push $DOCKER_HUB_REPO/$APPLICATION:$VERSION'	
				}
			
			}
		}
	}
	
	 stage('Deployment') {
      steps {
        container('maven') {
          dir("$APPLICATION") {
		  		sh 'kubectl -n $DEPLOY_NAMESPACE scale deployment $APPLICATION --replicas=0'
		 		 sleep 5
				sh 'jx step helm apply $APPLICATION --name $APPLICATION --namespace=$DEPLOY_NAMESPACE'
				}
			}
		}
		}  
   
  }
}
