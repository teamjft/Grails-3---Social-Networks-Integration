package com.jft

import grails.plugin.springsecurity.annotation.Secured

class DashboardController {

    def index() { }

    @Secured(value=["hasRole('ROLE_USER')"])
    def home() {

    }


}
