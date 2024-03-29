def getProjectMetadataFromDistInfo(){
    def metadataFile
    try{
        metadataFile = findFiles(excludes: '', glob: '*.dist-info/METADATA')[0]
    }catch(ArrayIndexOutOfBoundsException ex) {
        error 'Unable to locate METADATA file for a dist-info folder in workspace. Are you sure you created it?'
    }

    def package_metadata = readProperties interpolate: true, file: metadataFile.path
    echo """Metadata:

    Name      ${package_metadata.Name}
    Version   ${package_metadata.Version}
    """
    return package_metadata
}

return [
        getProjectMetadataFromDistInfo: this.&getProjectMetadataFromDistInfo
]
