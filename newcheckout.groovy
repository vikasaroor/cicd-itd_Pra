def call(Map config){
        echo "my URL is ${config.url}"
        echo "my URL is ${config.branch}"
        if (config.branch == "Main") {
        checkout([
                    $class: 'GitSCM',
                    branches: [[name: config.branch ]],
                    doGenerateSubmoduleConfigurations: false,
                    extensions: [],
                    userRemoteConfigs: [[
                        url: config.url,
                        credentialsId: config.cred
                    ]]
                ]) 
        else {
            echo "If branch other then main"
        }
}
}

// def config =  {
//     url = 'https://github.com/vikasaroor/cicd_itd_prac.git'
//     cred = 'github-cred'
//     branch = 'main'
// }