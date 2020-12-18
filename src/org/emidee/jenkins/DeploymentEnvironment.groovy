#!/usr/bin/groovy

package org.emidee.jenkins;

enum DeploymentEnvironment {
    Development,
    Release,
    Shipping,
    PullRequest
}