#!/usr/bin/groovy

def call( Script script, String github_token, String repository_owner, String repository_name, String pull_request_id ) {
    
    String url = "https://api.github.com/repos/${repository_owner}/${repository_name}/pulls/${pull_request_id}/merge"
    merge_result = httpRequest customHeaders: [[maskValue: false, name: 'Accept', value: 'application/vnd.github.v3+json'], [maskValue: false, name: 'Authorization', value: 'token ' + github_token ]], httpMode: 'PUT', responseHandle: 'NONE', url: url, wrapAsMultipart: false

    return merge_result
}