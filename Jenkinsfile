@Library('checkout_shared') _



pipeline {

    agent any 


    options {
        buildDiscarder(logRotator(    
            numToKeepStr: '6',      
        ))
    }    

parameters {
  booleanParam description: 'If sonar plugin', name: 'with_sonar_plugin', defaultValue: true
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
                  expression { return params.with_sonar_plugin == true }
                }  
            steps{
                script {
                  scannerHome = tool 'my-sonar-plug'
                }
                withSonarQubeEnv('sonar-remote') {
                sh "${scannerHome}/bin/sonar-scanner -D sonar.sources=./node_backend-main -D sonar.projectKey=node_backend"  
                }
            }
        }


        stage('sonar-qualitygates'){
            steps{
              timeout(time: 10, unit: 'MINUTES') {
                waitForQualityGate abortPipeline: true
                }
            }
        }


        stage('trivy fs check'){
            steps{
                sh '''
                    wget wget https://raw.githubusercontent.com/aquasecurity/trivy/main/contrib/html.tpl
                    trivy fs . --format template --template "@html.tpl" -o report.html
 
                '''
            }
        }
        
    }

    post {
        always{
            cleanWs()
        }
    }
}
