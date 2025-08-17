@Library('checkout_shared') _



pipeline {

    agent any 


    options {
        buildDiscarder(logRotator(
            daysToKeepStr: '7',      // Keep builds for 7 days
            numToKeepStr: '10',      // Or keep last 10 builds
            artifactDaysToKeepStr: '5',
            artifactNumToKeepStr: '2'
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
                    
                    trivy fs . --format template --template "@/tmp/html.tpl" -o report.html

                    publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, icon: '', 
                    keepAll: false, reportDir: '', reportFiles: 'report.html', 
                    reportName: 'HTML Report', reportTitles: '', useWrapperFileDirectly: true])
 
                '''
            }
        }


// /        stage{
// /            steps{
// /            // This step should not normally be used in your script. Consult the inline help for details.
// /withDockerRegistry(credentialsId: 'docker-cred') {
// //     // some block
// / }

    //         }
    //     }
        
    // }

    post {
        always{
            cleanWs()
        }
    }
}
