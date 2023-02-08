def getProjectMetadataFromDistInfo(){
    def metadataFile = findFiles(excludes: '', glob: '*.dist-info/METADATA')[0]
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
