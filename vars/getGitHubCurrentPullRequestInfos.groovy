#!/usr/bin/groovy

def call( Script script, String github_token, String repository_owner, String repository_name ) {
    def pull_request_id = getCurrentBranchGitHubPullRequestId()
    print "Current PR ID : ${pull_request_id}"
    return getGitHubPullRequestInfos( this, github_token, "FishingCactus", repository_name, pull_request_id )
}