package user

import com.auth.Client
import com.auth.Role
import com.auth.User
import com.auth.UserRole
import grails.transaction.Transactional
import util.CodeConstants
import util.ServiceContext

@Transactional
class UserManagementService {

    def sessionRegistry

    void bootstrapSystemRoles(){

        /* Create the SUPER_ADMIN user role. */
        if(!Role.findByAuthority(CodeConstants.ROLE_SUPER_ADMIN)) {
            def superAdminRole = new Role(authority: CodeConstants.ROLE_SUPER_ADMIN).save(flush: true)
        }

        /* Create the ADMIN user role. */
        if(!Role.findByAuthority(CodeConstants.ROLE_ADMIN)) {
            def adminRole = new Role(authority: CodeConstants.ROLE_ADMIN).save(flush: true)
        }

        /* Create a super admin user. */
        User superAdmin = User.findByUsername(CodeConstants.SUPER_ADMIN_USER_NAME)
        if(!superAdmin) {
            superAdmin = new User(
                    username        : CodeConstants.SUPER_ADMIN_USER_NAME,
                    password        : 'thete',
                    enabled         : true)

            superAdmin.save(flush: true, failOnError: true)

            def superAdminRole = Role.findByAuthority(CodeConstants.ROLE_SUPER_ADMIN)
            UserRole superAdminUserRole = UserRole.get(superAdmin.id, superAdminRole.id)
            if(!superAdminUserRole) {
                UserRole.create(superAdmin, superAdminRole, true)
            }
        }

        /* Create an admin user. */
        User admin = User.findByUsername(CodeConstants.ADMIN_USER_NAME)
        if(!admin) {
            admin = new User(
                    username        : CodeConstants.ADMIN_USER_NAME,
                    password        : 'prakash',
                    enabled         : true
            )
            admin.save(flush: true)
            def adminRole = Role.findByAuthority(CodeConstants.ROLE_ADMIN)
            UserRole adminUserRole = UserRole.get(admin.id, adminRole.id)
            if(!adminUserRole) {
                UserRole.create(admin, adminRole, true)
            }
        }



        /*---------------------------- Spring Security Oauth2 -------------------------------------*/

        Role roleUser = new Role(authority: 'ROLE_USER').save(flush: true)

        User user = new User(
                username: 'user',
                password: 'password',
                enabled: true,
                accountExpired: false,
                accountLocked: false,
                passwordExpired: false
        ).save(flush: true)

        UserRole.create(user, roleUser, true)

        new Client(
                clientId: 'client',
                authorizedGrantTypes: ['authorization_code', 'refresh_token', 'implicit', 'password', 'client_credentials'],
                authorities: ['ROLE_TRUSTED_CLIENT'],
                scopes: ['trust', 'read', 'write'],
                redirectUris: ['http://192.168.1.67:8080/SecuredOAuth2Resources']
        ).save(flush: true)

    }

    User findUserByUsername(ServiceContext sCtx, String userName) {
        try {
            return User.findByUsername(userName)
        } catch (Exception e) {
            println "Exception while finding user by its userName -> findUserByUsername" + e.printStackTrace()
        }
    }
}
