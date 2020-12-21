#!/usr/bin/groovy

import groovy.json.JsonSlurper

@NonCPS
def call( String github_token, String repository_owner, String repository_name, String pull_request_id ) {
    String url = "https://api.github.com/repos/${repository_owner}/${repository_name}/pulls/${pull_request_id}"
    
    def json_string = url.toURL().getText( requestProperties: [ 'Authorization' : "token ${github_token}" ] )
    def pull_request_infos = new JsonSlurper().parseText( json_string )

    return pull_request_infos
}