@Library('checkout_shared') _



pipeline {

    agent any 

parameters {
  booleanParam description: 'If sonar plugin', name: 'with-sonar-plugin'
}

    stages{
        stage('getCode'){
            steps{
                script{
                    def config = [
                        url: 'https://github.com/vikasaroor/cicd-itd_Prac.git' ,
                        cred: 'github-cred',
                        branch: 'main'
                    ]
                    newcheckout(config)
                }
            }
        }

        stage('sonar scan') {
          when {
            expression ( params.with-sonar-plugin == true )
          }  
            steps{
        script {
            scannerHome = tool 'my-sonar-plug'
        }
        withSonarQubeEnv('sonar-remote') {
          sh "${scannerHome}/bin/sonar-scanner -D sonar.projectKey=node_backend"  
        }
        }
            
        }
    }
}