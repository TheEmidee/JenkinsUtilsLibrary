#!/usr/bin/groovy

package jenkinsutils;

enum BranchType {
    Development,
    Release,
    Master,
    PullRequest
}

enum DeploymentEnvironment {
    Development,
    Release,
    Shipping,
    PullRequest
}

enum BuildConfiguration {
    Development,
    Test,
    Shipping,
    DebugGame
}

return this