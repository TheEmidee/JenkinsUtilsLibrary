#!/usr/bin/groovy

import com.emidee.jenkins.BranchType
import com.emidee.jenkins.BuildConfiguration
import com.emidee.jenkins.DeploymentEnvironment

def types = load 'types.groovy'

def call( script, String project_name_override = null ) {
    log.info "InitializeEnvironment"

    branch_type = getBranchType( script.env.BRANCH_NAME )
    deployment_environment = getBranchDeploymentEnvironment( branch_type )
    client_config = getClientConfig( deployment_environment )

    if ( project_name_override != null ) {
        script.env.PROJECT_NAME = project_name_override
    } else {
        script.env.PROJECT_NAME = getProjectName( script )
    }

    log.info "ProjectName : ${script.env.PROJECT_NAME}"

    script.env.BRANCH_TYPE = branch_type
    log.info "BranchType : ${script.env.BRANCH_TYPE}"

    script.env.DEPLOYMENT_ENVIRONMENT = deployment_environment
    log.info "DeploymentEnvironment ${script.env.DEPLOYMENT_ENVIRONMENT}"

    script.env.CLIENT_CONFIG = client_config
    log.info "ClientConfiguration : ${script.env.CLIENT_CONFIG}"
}

private def getProjectName(def script) {
    split_result = "${script.env.JOB_NAME}".split('/')
    project_name = split_result.length > 1 ? split_result[split_result.length - 2] : split_result.max()
    return project_name
}

private def getBranchType( String branch_name ) {
    if ( branch_name =~ ".*develop" ) {
        return BranchType.Development
    } else if ( branch_name =~ ".*release/.*" ) {
        return BranchType.Release
    } else if ( branch_name =~ ".*master" ) {
        return BranchType.Master
    }

    return BranchType.PullRequest
}

private def getBranchDeploymentEnvironment( BranchType branch_type ) {
    switch ( branch_type ) {
        case BranchType.Development:
            return DeploymentEnvironment.Development
        case BranchType.Release:
            return DeploymentEnvironment.Release
        case BranchType.Master:
            return DeploymentEnvironment.Shipping
        default:
            return DeploymentEnvironment.PullRequest
    }
}

def getClientConfig( DeploymentEnvironment deployment_environment ) {
    switch ( deployment_environment ) {
        case DeploymentEnvironment.Shipping:
            return BuildConfiguration.Shipping
        default:
            return BuildConfiguration.Development
    }
}