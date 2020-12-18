package org.emidee.jenkins;

import groovy.transform.Field

class environment {
    BranchType BRANCH_TYPE = BranchType.Development
    BuildConfiguration BUILD_CONFIGURATION = BuildConfiguration.Development
    DeploymentEnvironment DEPLOYMENT_ENVIRONMENT = DeploymentEnvironment.Development
    String PROJECT_NAME = ""
}