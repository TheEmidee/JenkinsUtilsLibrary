#!/usr/bin/groovy

def call() {
    def branch_name = getBranchName()
    def pr_id = branch_name.substring( 3 )
    // echo "PR ID : ${pr_id}"

    return pr_id
}