#!/usr/bin/groovy

def call( String project_name_override = null, body ) {
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

def call( Map parameters = [:], body ) {

    def defaultLabel = buildId('release')
    def label = parameters.get('label', defaultLabel)

    releaseTemplate(parameters) {
        node(label) {
            body()
        }
    }
}