```bash
#!/bin/bash

# Modern EGP Application Deployment Script
# Supports JBoss AS 7.x and WebLogic 12c

set -euo pipefail

# Constants
readonly PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
readonly EAR_FILE="$PROJECT_ROOT/egp-ear/target/egp-ear-1.0.0-SNAPSHOT.ear"
readonly LOG_FILE="$PROJECT_ROOT/deploy.log"

# Environment configurations
declare -A JBOSS_CONFIGS=(
    [dev]="localhost:9999"
    [test]="egp-test.internal.corp:9999"
    [prod]="egp-prod.internal.corp:9999"
)

declare -A WEBLOGIC_CONFIGS=(
    [dev]="t3://localhost:7001"
    [test]="t3://egp-test.internal.corp:7001"
    [prod]="t3://egp-prod.internal.corp:7001"
)

# Functions
log() {
    local msg="$1"
    echo "$(date +'%Y-%m-%d %H:%M:%S') - $msg" | tee -a "$LOG_FILE"
}

usage() {
    echo "Usage: $0 {deploy|undeploy} {dev|test|prod} {jboss|weblogic}"
    exit 1
}

deploy_jboss() {
    local env="$1"
    local server="${JBOSS_CONFIGS[$env]}"

    log "Deploying EGP application to JBoss AS at $server"
    # Example deployment command for JBoss
    jb_cli_command="deploy $EAR_FILE --server=$server"
    if ! eval "$jb_cli_command"; then
        log "Deployment to JBoss failed"
        exit 1
    fi
    log "Deployment to JBoss successful"
}

undeploy_jboss() {
    local env="$1"
    local server="${JBOSS_CONFIGS[$env]}"

    log "Undeploying EGP application from JBoss AS at $server"
    # Example undeployment command for JBoss
    jb_cli_command="undeploy egp-ear-1.0.0-SNAPSHOT.ear --server=$server"
    if ! eval "$jb_cli_command"; then
        log "Undeployment from JBoss failed"
        exit 1
    fi
    log "Undeployment from JBoss successful"
}

deploy_weblogic() {
    local env="$1"
    local server="${WEBLOGIC_CONFIGS[$env]}"

    log "Deploying EGP application to WebLogic at $server"
    # Example deployment command for WebLogic
    wl_cli_command="java weblogic.Deployer -adminurl $server -user myuser -password mypassword -deploy -source $EAR_FILE"
    if ! eval "$wl_cli_command"; then
        log "Deployment to WebLogic failed"
        exit 1
    fi
    log "Deployment to WebLogic successful"
}

undeploy_weblogic() {
    local env="$1"
    local server="${WEBLOGIC_CONFIGS[$env]}"

    log "Undeploying EGP application from WebLogic at $server"
    # Example undeployment command for WebLogic
    wl_cli_command="java weblogic.Deployer -adminurl $server -user myuser -password mypassword -undeploy egp-ear-1.0.0-SNAPSHOT"
    if ! eval "$wl_cli_command"; then
        log "Undeployment from WebLogic failed"
        exit 1
    fi
    log "Undeployment from WebLogic successful"
}

# Main script execution
if [[ "$#" -ne 3 ]]; then
    usage
fi

action="$1"
environment="$2"
server_type="$3"

log "Starting $action for environment $environment on $server_type server"

case "$server_type" in
    jboss)
        if [[ "$action" == "deploy" ]]; then
            deploy_jboss "$environment"
        elif [[ "$action" == "undeploy" ]]; then
            undeploy_jboss "$environment"
        else
            usage
        fi
        ;;
    weblogic)
        if [[ "$action" == "deploy" ]]; then
            deploy_weblogic "$environment"
        elif [[ "$action" == "undeploy" ]]; then
            undeploy_weblogic "$environment"
        else
            usage
        fi
        ;;
    *)
        usage
        ;;
esac

log "$action for environment $environment on $server_type server completed"
```