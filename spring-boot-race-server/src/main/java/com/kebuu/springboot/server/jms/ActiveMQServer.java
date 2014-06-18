package com.kebuu.springboot.server.jms;


import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.security.AuthorizationEntry;
import org.apache.activemq.security.AuthorizationPlugin;
import org.apache.activemq.security.SimpleAuthenticationPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ActiveMQServer {

    public static void main(String[] args) throws Exception {
        BrokerService service = new BrokerService();
        service.addConnector("tcp://localhost:61616");
        service.setPersistent(false);

        Map<String, String> usersPassword = new HashMap<String, String>() {{
            this.put("zenika", "technozaure");
            this.put("admin", "admin");
        }};

        Map<String, String> usersRoles = new HashMap<String, String>() {{
            this.put("zenika", "user");
            this.put("admin", "admin");
        }};

        SimpleAuthenticationPlugin simpleAuthenticationPlugin = new SimpleAuthenticationPlugin();
        simpleAuthenticationPlugin.setUserPasswords(usersPassword);
        //SimpleAuthenticationPlugin.installPlugin(service.getBroker());

        AuthorizationPlugin authorizationPlugin  = new AuthorizationPlugin();
        AuthorizationEntry authorizationEntry = new AuthorizationEntry();
        authorizationEntry.setRead("user");
        authorizationEntry.setWrite("admin");
        Set<AuthorizationEntry> readACLs = (Set<AuthorizationEntry>) authorizationPlugin.getMap().getReadACLs(new ActiveMQTopic("spring.boot.race.step3"));
        readACLs.add(authorizationEntry);

        //authorizationPlugin.installPlugin(service.getBroker());

        service.start();
    }
}
