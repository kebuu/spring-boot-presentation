package com.kebuu.springboot.repository;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.kebuu.springboot.RemoteShellApplication;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=RemoteShellApplication.class)
@WebAppConfiguration
@IntegrationTest
public class RemoteShellApplicationTest {

    @Test
    public void testShell() throws JSchException {
        JSch jSch = new JSch();
        Session session = jSch.getSession("zenika", "127.0.1", 2222);
        session.setPassword("technozaure");
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        Assertions.assertThat(session.isConnected()).isTrue();
    }
}
