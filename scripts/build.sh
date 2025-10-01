```bash
#!/bin/bash

# EGP Application Build Script
# Supports both JBoss AS 7.x and WebLogic 12c deployments

set -e

PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
BUILD_DIR="$PROJECT_ROOT/target"
TIMESTAMP=$(date +"%Y%m%d_%H%M%S")

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Logging function
log() {
    echo -e "${GREEN}[$(date +'%Y-%m-%d %H:%M:%S')] $1${NC}"
}

warn() {
    echo -e "${YELLOW}[$(date +'%Y-%m-%d %H:%M:%S')] $1${NC}"
}

error() {
    echo -e "${RED}[$(date +'%Y-%m-%d %H:%M:%S')] $1${NC}"
}

log "Starting build process..."

# Clean previous builds
log "Cleaning previous builds..."
rm -rf "$BUILD_DIR"
mkdir -p "$BUILD_DIR"

# Run Maven build
log "Running Maven build..."
if ! mvn clean install; then
    error "Maven build failed."
    exit 1
fi

log "Maven build completed successfully."

# Deployment logic
deploy_to_servers() {
    if [ -d "$JBOSS_HOME" ]; then
        log "Deploying to JBoss..."
        # JBoss deployment commands
    fi
    
    if [ -d "$WEBLOGIC_HOME" ]; then
        log "Deploying to WebLogic..."
        # WebLogic deployment commands
    fi
}

deploy_to_servers

log "Build and deployment process completed."
exit 0
```