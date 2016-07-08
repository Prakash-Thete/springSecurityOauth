package springsecuritysecond

import grails.plugin.springsecurity.annotation.Secured
import util.RestSessionUtil
import util.ServiceContext

class ProvisioningController {

    def springSecurityService
    def cookieService
    def userManagementService

    @Secured(['ROLE_SUPER_ADMIN', 'ROLE_ADMIN'])
    def index() {
        ServiceContext sCtx = RestSessionUtil.getServiceContext(request, springSecurityService, userManagementService)
        String cookieName = grailsApplication.config.cookieName

        cookieService.setCookie( cookieName, (sCtx.userId + ":" + 24 * 60 ) , 24*60 )
        redirect(action: 'showDetails')
    }

    @Secured(['ROLE_SUPER_ADMIN', 'ROLE_ADMIN'])
    def showDetails(){
        println "Set Cookie : " + cookieService.get('loginCookie')
        [data : "done"]
    }
}
