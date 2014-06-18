package com.kebuu.springboot.server.jms;


import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.filter.DestinationMap;
import org.apache.activemq.security.AuthorizationEntry;
import org.apache.activemq.security.AuthorizationPlugin;
import org.apache.activemq.security.SimpleAuthenticationPlugin;
import org.apache.activemq.security.SimpleAuthorizationMap;

import java.util.HashMap;
import java.util.Map;

public class ActiveMQServer {

    public static void main(String[] args) throws Exception {
        BrokerService service = new BrokerService();
        service.addConnector("tcp://localhost:61616");
        service.setPersistent(false);

        Map<String, String> usersPassword = new HashMap<String, String>() {{
            this.put("zenika", "technozaure");
            this.put("admin", "ctardella");
        }};

        Map<String, String> usersRoles = new HashMap<String, String>() {{
            this.put("zenika", "user");
            this.put("admin", "admin");
        }};

        SimpleAuthenticationPlugin simpleAuthenticationPlugin = new SimpleAuthenticationPlugin();
        simpleAuthenticationPlugin.setUserPasswords(usersPassword);
        simpleAuthenticationPlugin.installPlugin(service.getBroker());


        AuthorizationEntry authorizationEntry = new AuthorizationEntry();
        authorizationEntry.setRead("user,admin");
        authorizationEntry.setWrite("admin");

        DestinationMap readAcls = new DestinationMap();
        readAcls.put(new ActiveMQTopic("spring.boot.race.step3"), authorizationEntry);

        SimpleAuthorizationMap authorizationMap = new SimpleAuthorizationMap();
        authorizationMap.setReadACLs(readAcls);

        AuthorizationPlugin authorizationPlugin  = new AuthorizationPlugin();
        authorizationPlugin.setMap(authorizationMap);
        authorizationPlugin.installPlugin(service.getBroker());

        service.start();
    }
}
