#!/usr/bin/groovy

def call() {
    // maybe not possible to access env here?
    def branch_name = env.BRANCH_NAME

    if ( branch_name == null ) {
        branch_name = env.GIT_BRANCH
    }

    return branch_name
}