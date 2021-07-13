#!/usr/bin/groovy

def call( source, destination, arguments ) {
    def status = bat returnStatus: true, script: "robocopy.exe ${source} ${destination} ${arguments}"
    def success = !(status < 0 || status > 3)

    if ( !success ) {
        log.error( "ROBOCOPY failed" )
    }

    return success
}