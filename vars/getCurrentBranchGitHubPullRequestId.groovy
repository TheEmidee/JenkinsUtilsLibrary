#!/usr/bin/groovy

def call() {
    String result = ""

    def branch_name = getBranchName()
    
    if ( branch_name.startsWith( "PR-" ) ) {
        result = branch_name.substring( 3 )
    }

    return result
}