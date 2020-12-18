package org.emidee.jenkins;

import groovy.transform.Field

class environment implements Serializable {
    public BranchType BRANCH_TYPE = BranchType.Development
    public BuildConfiguration BUILD_CONFIGURATION = BuildConfiguration.Development
    public DeploymentEnvironment DEPLOYMENT_ENVIRONMENT = DeploymentEnvironment.Development
    public String PROJECT_NAME = ""
}