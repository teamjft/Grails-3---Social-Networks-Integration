package com.jft

import com.google.api.services.plus.model.Person
import com.jft.security.Role
import com.jft.security.User
import com.jft.security.UserRole
import grails.plugin.springsecurity.SpringSecurityService
import org.springframework.beans.factory.annotation.Autowired

class RegisterController {
    private static final String URL_GOOGLE_API = "https://accounts.google.com/o/oauth2/v2/auth?redirect_uri=http://localhost:8080/oauth/register/googleOAuth2Callback&prompt=consent&response_type=code&client_id=284123898988-nr7n0sf00trr36dsj7munem0ujedakj2.apps.googleusercontent.com&" +
            "scope=https://www.googleapis.com/auth/userinfo.profile " +
            "https://www.googleapis.com/auth/userinfo.email " +
            "https://www.googleapis.com/auth/plus.login" +
            "&access_type=offline"

    @Autowired
    GoogleApiController googleApiController;
    @Autowired
    UserService userService;
    @Autowired
    BuilderService builderService;
    @Autowired
    SpringSecurityService springSecurityService;

    def register() {
        User user = userService.save(builderService.buildUserFromParams(params));
        Role role = Role.findByAuthority("ROLE_USER");
        UserRole userRole = new UserRole(user: user, role: role);
        userService.saveUserRole(userRole);
        if (user) {
            springSecurityService.reauthenticate(params.email)
            redirect(controller: 'dashboard', action: 'home');
        } else {
            render "Error"
        }
    }


    def usingGoogle() {
        redirect(url:URL_GOOGLE_API)
    }

    def usingFacebook() {
        render "Facebook"
    }


    def googleOAuth2Callback(String code) {
        String accessToken = googleApiController.exchangeAuthorizationCodeForToken(code);
        Person person= googleApiController.getUserInfo(accessToken);
        println person.getEmails()
        User user = userService.save(builderService.buildUser(person));
        Role role = Role.findByAuthority("ROLE_USER");
        UserRole userRole = new UserRole(user: user, role: role);
        userService.saveUserRole(userRole);
        springSecurityService.reauthenticate(user.getUsername())
        redirect (controller: 'dashboard', action: 'home');
    }
}
