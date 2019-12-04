#Define the Snapshot repository
#Define the groupId
groupID=$1
#Define the aryifactId
artifactId=$2
#Define the version
version=$3
#Define the extension
extension=$4


#Declare the nexus repository path
path=http://nexus.jx.35.229.61.119.nip.io/repository/maven-snapshots/${groupID}/${artifactId}/${version}

#fetch the artifact ID for the latest version from the metadata file
artifactid=`curl -s $path/maven-metadata.xml |  grep artifactId  | sed "s/.*<artifactId>\([^<]*\)<\/artifactId>.*/\1/"`
echo $artifactid
#fetch the latest timestamp that was built and uploaded
value=`curl -s $path/maven-metadata.xml | grep '<value>' | head -1 | sed "s/.*<value>\([^<]*\)<\/value>.*/\1/"`
echo $value
#Set the build files location
#Download the artifact to build files path from Nexus Repository
wget -O poclistener.jar  $path/${artifactId}-${value}.${extension}
