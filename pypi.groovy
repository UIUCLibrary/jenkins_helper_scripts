def pypiUpload(args = [:]){
    def credentialsId = args['credentialsId']
    withEnv(
        [
            "TWINE_REPOSITORY_URL=${args['repositoryUrl']}",
            "DEST=${args['glob']}",
        ]
    ){
        withCredentials(
            [
                usernamePassword(
                    credentialsId: credentialsId,
                    passwordVariable: 'TWINE_PASSWORD',
                    usernameVariable: 'TWINE_USERNAME'
                )
            ]){
                if(isUnix()){
                    sh(label: 'Uploading to pypi', script: 'twine upload --disable-progress-bar --non-interactive $DEST')
                } else {
                    bat(label: 'Uploading to pypi', script: 'twine upload --disable-progress-bar --non-interactive %DEST%')
                }
            }
    }
}
return this
