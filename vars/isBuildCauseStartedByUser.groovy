#!/usr/bin/groovy

def call() {
    def buildCause = currentBuild.buildCauses.shortDescription[ 0 ]
    return buildCause.startsWith( "Started by user" )
}