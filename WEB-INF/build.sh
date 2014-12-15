#!/bin/bash



# ================= Globals ==============

# Various Paths
tomcatJarPath="/Users/pairofham/code_packages/apache-tomcat-7.0.47/lib/*"

basePath="."
srcPath="$basePath/src"
buildPath="$basePath/classes"

# Command line flag variables
bFlag=0		# Build
cFlag=0		# Clean




# ================= Functions ==============

function displayUsage {
	echo $1
	echo "Usage: ./control.sh"
	echo -e "\t-b: build source files"
	echo -e "\t-c: clean"
	exit
}

# Compiles .java file argument using CLASSPATH environment variable
# Puts resulting .class files in 
function compileJava {
	javac -d $buildPath $1
	if [ $? -ne 0 ]; then
		exit $?
	fi
}



# ================= Runtime ==============

# check which options are set
while getopts :bc opt; do
	case $opt in
		b ) bFlag=1;;
		c ) cFlag=1;;
		\? ) displayUsage "Unknown flag -$OPTARG";;
		:) displayUsage "-$OPTARG requires an argument";;
	esac
done

# Store existing classpath environment variable
oldClasspath=$CLASSPATH

# Add needed elements to classpath
export CLASSPATH=.:$tomcatJarPath

# Compile updated classes and push into jar
if [ $bFlag -eq 1 ]; then
	# Create directory for updates at $updatesPath if necessary
	if [ ! -d $buildPath ]; then
		mkdir $buildPath
		echo "Created directory $buildPath"
	fi

	# Compile all .java files in $srcPath
	echo "Compiling .java files..."
	find $srcPath -name "*.java" | while read line
	do
		echo -ne "\t$line..."
		compileJava $line
		echo "done"
	done
fi

if [ $cFlag -eq 1 ]; then
	echo -n "Cleaning..."
	rm -rf $buildPath/
	echo "done"
fi

# Restore original classpath environment variable
export CLASSPATH=$oldClasspath