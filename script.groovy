def buildApp() {
	echo 'building the application...'
}

def testApp() {
	echo 'testing the application...'
}

def deployApp() {
	echo 'deploying the application...'
	echo "deploying version ${params.VERSION}"
}

return this	//We must return here, else we cannot import it into the Jenkins file
