//CODE_CHANGES = getGitChanges()  //<-- some groovy script that checks whether there has been any code change
pipeline {  //Required, must be top-level
  agent any //Required - where to execute
  tools { //Build tools for your project: gradle, maven, jdk (only these three are available per default)
    maven 'Maven' // <what you wanna use> <The name as it is defined in Jenkins (under 'Manage Jenkins'->'Globasl Tool Configuration')>. This will make maven commands available in all stages
  }
  environment { // All variables defined here will be availble for all the stages of the pipeline
    NEW_VERSION = '1.3.0'
   // SERVER_CREDENTISALS = credentials('server-credentials') // <-- Fetches the credentials from Jenkins. Need to install 'Credentials Binding' plugin for this to work.
    //If the credentials are going to be used only once we can declare them in the stage instead
  }
  parameters {
    //string(name: 'VERSION', defaultValue: '', description: 'version to deploy to prod')
    choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description: '')
    booleanParam(name: 'executeTests', defaultValues: true, description: '')
  }
  
  stages {  //Required - where the work happens
    stage("build") {  //Can declare as many stages as we like
      /*
      when {
        expression {
          BRANCH_NAME == 'dev' && CODE_CHANGES == true
        }
      }
      */
      steps {
        echo 'building the application'
        //sh 'mvn install ..'
        echo "building version ${NEW_VERSION}"  //<-- OBS, must be double quotes, else it will be read as a string not a variable
      }
    }
    
    stage("test") {
      when {
        expression {
          //BRANCH_NAME or env.BRANCH_NAME is always available for you.
          //BRANCH_NAME == 'dev' | BRANCH_NAME == 'master'  // Only run stage if it's the ev or master branch that is being built
          params.executeTests //params.executeTests == true , then execute this stage
        }
      }
      steps {
        echo 'testing the application'
      }
    }
    
    stage("deploy") {
      steps {
        echo 'deploying the application'
        echo "deploying with ${SERVER_CREDENTISALS}"  //Of course we won't print the credentials here if it was a real project
        sh "${SERVER_CREDENTISALS}"
        
        echo "deploying version ${params.VERSION}"
        
        withCredentials([
          //Stores the user name into the 'USER' variable and the password into 'PWD'
            usernamePassword(credentials: 'server-credentials', usernameVariable: USER, passwordVariable: PWD) //The type of credential we created in Jenkins is 'Username with password'
        ]) {
          sh "some script ${USER} ${PWD}"
        }
      }
    }
  }
  post {  // Runs after all the stages are done. Specify logic that should under some condition (build status or build status change)
    always {
      //Is always run, no matter if the build succeeded or failed
      //T.ex sending an email to the dev team with the status of the build
    }
    success {
      //Only runs on success
    }
    failure {
      //Only run on failure
    }
  }
}
