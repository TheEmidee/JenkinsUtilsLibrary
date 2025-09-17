#!/usr/bin/groovy

def call( Script script, String github_token, String repository_owner, String repository_name, String pull_request_id ) {
    assert pull_request_id != null
    assert pull_request_id != ""

    String url = "https://api.github.com/repos/${repository_owner}/${repository_name}/pulls/${pull_request_id}"
    
    // Debug: check if token exists (don't print the actual token for security)
    script.echo "Token exists: ${github_token != null && !github_token.isEmpty()}"
    script.echo "Token length: ${github_token.length()}"
    script.echo "Token starts with: ${github_token.take(4)}..." // Shows first 4 chars
    script.echo "URL: ${url}"

    def json_string = url.toURL().getText( requestProperties: [ 
        'Authorization' : "token ${github_token}" ,
        'User-Agent': 'Jenkins-Pipeline'
        ] )
    
    def pull_request_infos = script.readJSON text: json_string

    return pull_request_infos
}