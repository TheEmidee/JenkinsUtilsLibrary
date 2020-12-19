#!/usr/bin/groovy

import groovy.json.JsonSlurper

def call( String github_token, String repository_owner, String repository_name, String pull_request_id ) {
    String url = "https://api.github.com/repos/${repository_owner}/${repository_name}/pulls/${pull_request_id}"
    
    def text = url.toURL().getText( requestProperties: [ 'Authorization' : "token ${github_token}" ] )
    def json = new JsonSlurper().parseText( text )

    return json
}