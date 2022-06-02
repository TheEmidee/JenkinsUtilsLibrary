#!/usr/bin/groovy

import org.emidee.jenkins.BranchType
import org.emidee.jenkins.BuildConfiguration
import org.emidee.jenkins.DeploymentEnvironment
import org.emidee.jenkins.Environment

def call( Script script, String project_name_override = null ) {
    log.info "InitializeEnvironment"

    log.info env.NODE_NAME

    def branch_name = getBranchName()
    log.info "BranchName : ${branch_name}"

    Environment.instance.BRANCH_NAME = branch_name

    def branch_type = getBranchType( branch_name )
    def deployment_environment = getBranchDeploymentEnvironment( branch_type )
    def build_configuration = getBuildConfiguration( deployment_environment )

    if ( project_name_override != null ) {
        Environment.instance.PROJECT_NAME = project_name_override
    } else {
        Environment.instance.PROJECT_NAME = getProjectName( script )
    }

    log.info "ProjectName : ${Environment.instance.PROJECT_NAME}"

    Environment.instance.BRANCH_TYPE = branch_type
    log.info "BranchType : ${Environment.instance.BRANCH_TYPE}"

    Environment.instance.DEPLOYMENT_ENVIRONMENT = deployment_environment
    log.info "DeploymentEnvironment ${Environment.instance.DEPLOYMENT_ENVIRONMENT}"

    Environment.instance.BUILD_CONFIGURATION = build_configuration
    log.info "ClientConfiguration : ${Environment.instance.BUILD_CONFIGURATION}"
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

private def getBuildConfiguration( DeploymentEnvironment deployment_environment ) {
    switch ( deployment_environment ) {
        case DeploymentEnvironment.Shipping:
            return BuildConfiguration.Shipping
        default:
            return BuildConfiguration.Development
    }
}