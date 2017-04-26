package social_networks_integration

import com.jft.security.Role

class BootStrap {

    def init = { servletContext ->
        Role admin = new Role("ROLE_ADMIN");
        admin.save(failOnError: true,flush:true);
        Role user = new Role("ROLE_USER");
        user.save(failOnError: true,flush:true);
    }
    def destroy = {
    }
}
