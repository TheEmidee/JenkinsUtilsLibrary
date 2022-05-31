#!/usr/bin/groovy

def call( Script script ) {
    return script.bat (
        script: "git --no-pager show -s --format=%%ae",
        returnStdout: true
    ).split('\r\n')[2].trim()
}