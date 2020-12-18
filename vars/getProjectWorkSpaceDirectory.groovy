#!/usr/bin/groovy

import org.emidee.jenkins.DeploymentEnvironment
import org.emidee.jenkins.Environment

def call( Script script ) {
    log.info "Initialize Node"

    // log.info "UE4_ROOT : ${script.env.NODE_UE4_ROOT}"
    // if ( script.env.NODE_UE4_ROOT == null || script.env.NODE_UE4_ROOT.isEmpty() ) {
    //     log.fatal "Missing environment variable NODE_UE4_ROOT. Add it to the node properties."
    // }

    // script.env.NODE_UE4_ROOT_WINDOWS = "${script.env.NODE_UE4_ROOT}\\Windows"

    def global_workspace = new File( script.env.WORKSPACE ).parent
    def project_workspace_name = Environment.instance.PROJECT_NAME
    project_workspace_name += ( Environment.instance.DEPLOYMENT_ENVIRONMENT as DeploymentEnvironment ) == DeploymentEnvironment.Shipping
        ? "_Master"
        : "_Develop"

    def workspace = new File( global_workspace, project_workspace_name )
    log.info "Workspace : ${workspace}"

    return workspace
}