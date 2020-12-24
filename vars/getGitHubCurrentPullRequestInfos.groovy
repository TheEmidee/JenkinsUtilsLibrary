#!/usr/bin/groovy

def call( Script script, String github_token, String repository_owner, String repository_name ) {
    def pull_request_id = getCurrentBranchGitHubPullRequestId()
    return getGitHubPullRequestInfos( this, GITHUB_ACCESS_TOKEN, "FishingCactus", Environment.instance.PROJECT_NAME, pull_request_id )
}