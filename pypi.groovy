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
                sh(label: 'Uploading to pypi', script: 'twine upload --non-interactive $DEST')
            }
    }
}
return this