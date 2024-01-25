#!/usr/bin/groovy

import org.emidee.jenkins.BranchType
import org.emidee.jenkins.BuildConfiguration
import org.emidee.jenkins.DeploymentEnvironment
import org.emidee.jenkins.Environment

def call( Script script ) {
    
    def node_workspace = pwd()
    def parent_workspace = new File( node_workspace ).parent
    def project_workspace_name = Environment.instance.PROJECT_NAME
    project_workspace_name += ( Environment.instance.DEPLOYMENT_ENVIRONMENT as DeploymentEnvironment ) == DeploymentEnvironment.Shipping
        ? "_Master"
        : "_Develop"

    def workspace = new File( parent_workspace, project_workspace_name )
    
    log.info "Workspace : ${workspace}"
    
    return workspace
}