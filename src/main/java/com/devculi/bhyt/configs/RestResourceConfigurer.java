package com.devculi.bhyt.configs;

import com.devculi.bhyt.entities.Bill;
import com.devculi.bhyt.entities.Category;
import com.devculi.bhyt.entities.Family;
import com.devculi.bhyt.entities.User;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;

@Component

public class RestResourceConfigurer implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Bill.class);
        config.exposeIdsFor(Category.class);
        config.exposeIdsFor(Family.class);
        config.exposeIdsFor(User.class);
    }
}
