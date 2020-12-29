#!/usr/bin/groovy

def info(message) {
    echo "INFO: ${message}"
}

def warning(message) {
    echo "WARNING: ${message}"
}

def error(message) {
    echo "ERROR: ${message}"
}

def fatal(message) {
    echo "FATAL: ${message}"
    currentBuild.result = "ABORTED"
    error( message )
}