import grails.plugin.springsecurity.SecurityFilterPosition
import grails.plugin.springsecurity.SpringSecurityUtils

class BootStrap {

    def userManagementService

    def init = { servletContext ->

        //Bootstrapping System Roles
//        SpringSecurityUtils.clientRegisterFilter('cookiePreAuthFilter', SecurityFilterPosition.PRE_AUTH_FILTER)

        userManagementService.bootstrapSystemRoles()
    }
    def destroy = {
    }
}
