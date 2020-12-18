#!/usr/bin/groovy

package jenkinsutils;

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

return this