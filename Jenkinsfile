@Library('checkout_shared') _
pipeline {

    agent any 

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
            steps{
                script {
            withSonarQubeEnv('sonar-remote') {
               
                 sh "sonar-scanner"
          }
            }
        }
            
        }
    }
}