@Library('checkout_shared') _
pipeline {

    agent any 

    stages{
        stage('getCode'){
            steps{
                script{
                    def mygitcheckout = {
                        url = 'https://github.com/vikasaroor/cicd_itd_prac.git'
                        cred = 'github-cred'
                        branch = 'main'
                    }
                    newcheckout(mygitcheckout)
                }
            }
        }
    }
}