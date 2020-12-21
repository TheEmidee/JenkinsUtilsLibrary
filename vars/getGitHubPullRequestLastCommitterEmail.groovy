#!/usr/bin/groovy

import groovy.json.JsonSlurper

def call( Script script, String github_token, String repository_owner, String repository_name, String pull_request_id ) {
    def json_string = getGitHubPullRequestCommits( github_token, repository_owner, repository_name, pull_request_id )
    def commits = script.readJSON text: json_string

    def commits_count = commits.size()
    def last_commit = commits[ commits_count - 1 ]
    log.info "Last commit SHA : ${last_commit.sha}"

    pr_last_committer_email = last_commit.commit.author.email
    log.info "Last commit author email : ${pr_last_committer_email}"

    return pr_last_committer_email
}