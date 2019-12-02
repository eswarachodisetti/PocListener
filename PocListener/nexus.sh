#Define the Snapshot repository
#Define the groupId
groupID=com/TestWebservice
#Define the aryifactId
artifactId=TestWebservice
#Define the extension
extension=war
#Define the tarname
reponame=0.0.1-SNAPSHOT
#get the input from jenkins
artifact=$1
#extract the revision from the input
#revision=`echo $artifact | grep -o '[0-9:]*'`
echo $revision
#Declare the nexus repository path
path=http://nexus.jx.35.229.61.119.nip.io/repository/maven-snapshots/com/TestWebservice/TestWebservice/0.0.1-SNAPSHOT/
#fetch the latest version that is available in nexus from the metadata file
version=`curl -s $path/maven-metadata.xml |  grep $revision | sed "s/.*<version>\([^<]*\)<\/version>.*/\1/"`
echo $version
#fetch the artifact ID for the latest version from the metadata file
artifactid=`curl -s $path/maven-metadata.xml |  grep artifactId  | sed "s/.*<artifactId>\([^<]*\)<\/artifactId>.*/\1/"`
echo $artifactid
#fetch the latest timestamp that was built and uploaded
value=`curl -s $path/$version/maven-metadata.xml | grep '<value>' | head -1 | sed "s/.*<value>\([^<]*\)<\/value>.*/\1/"`
echo $value
#Set the build files location
#Download the artifact to build files path from Nexus Repository
wget -O TestWebservice-0.0.1-SNAPSHOT.war  http://nexus.jx.35.229.61.119.nip.io/repository/maven-snapshots/${groupID}/${artifactid}/${reponame}/${version}/${artifactid}-${value}.${extension}
