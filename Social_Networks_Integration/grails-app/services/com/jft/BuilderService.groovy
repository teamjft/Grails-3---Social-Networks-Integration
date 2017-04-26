package com.jft

import com.google.api.services.plus.model.Person
import com.jft.security.User
import grails.web.servlet.mvc.GrailsParameterMap

class BuilderService {

    def User buildUser(Person person) {
        println person
        User user =  new User()
        user.firstName = person.getName().getGivenName()
        user.lastName = person.getName().getFamilyName()
        Person.Emails emails = person.getEmails().get(0);
        user.username = emails.getValue()
        user.gender = person.getGender()
        user.displayName = person.getDisplayName()
        user.profilePicture = person.getImage()
        user.password = "demo"
        return  user;

    }

    def User buildUserFromParams(GrailsParameterMap params) {
        User user = new User();
        user.username = params.email
        user.firstName = params.firstName
        user.lastName = params.lastName
        user.displayName = params.firstName+ " "+ params.lastName
        user.password = params.password
        user.gender = params.gender
        return user;
    }
}
