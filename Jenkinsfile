pipeline {  //Required, must be top-level
  agent any //Required - where to execute
  
  stages {  //Required - where the work happens
    stage("build") {  //Can declare as many stages as we like
      steps {
        echo 'building the application'
        echo 'application built'
      }
    }
    
    stage("test") {
      steps {
        echo 'testing the application'
      }
    }
    
    stage("deploy") {
      steps {
        echo 'deploying the application'
      }
    }
  }
}
