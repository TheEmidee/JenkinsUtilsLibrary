#!/usr/bin/groovy

package jenkinsutils;

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