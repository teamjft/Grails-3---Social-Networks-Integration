package com.jft

import com.jft.security.User
import com.jft.security.UserRole
import grails.transaction.Transactional

@Transactional
class UserService {

    User save(User user) {
        return user.save(failOnError: true,flush:true);
    }

    UserRole saveUserRole(UserRole userRole) {
       return userRole.save(failOnError: true,flush:true);
    }

}
