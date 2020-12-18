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

def initializeNode(Script script) {
    log.info "Initialize Node"

    log.info "UE4_ROOT : ${script.env.NODE_UE4_ROOT}"
    if ( script.env.NODE_UE4_ROOT == null || script.env.NODE_UE4_ROOT.isEmpty() ) {
        log.fatal "Missing environment variable NODE_UE4_ROOT. Add it to the node properties."
    }

    script.env.NODE_UE4_ROOT_WINDOWS = "${script.env.NODE_UE4_ROOT}\\Windows"

    global_workspace = new File( script.env.WORKSPACE ).parent
    project_workspace_name = script.env.PROJECT_NAME
    project_workspace_name += ( script.env.DEPLOYMENT_ENVIRONMENT as DeploymentEnvironment ) == DeploymentEnvironment.Shipping
        ? "_Master"
        : "_Develop"

    script.env.WORKSPACE = new File( global_workspace, project_workspace_name )
    log.info "Workspace : ${script.env.WORKSPACE}"
}

def getProjectName(def script) {
    split_result = "${script.env.JOB_NAME}".split('/')
    project_name = split_result.length > 1 ? split_result[split_result.length - 2] : split_result.max()
    return project_name
}

def getBranchType( String branch_name ) {
    if ( branch_name =~ ".*develop" ) {
        return BranchType.Development
    } else if ( branch_name =~ ".*release/.*" ) {
        return BranchType.Release
    } else if ( branch_name =~ ".*master" ) {
        return BranchType.Master
    }

    return BranchType.PullRequest
}

def getBranchDeploymentEnvironment( BranchType branch_type ) {
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

return this