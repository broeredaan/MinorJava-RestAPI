package com.ajp.yourgrade.controller;

import com.ajp.yourgrade.model.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class RestControllerTest {
    @Test
    public ResponseEntity<String> status() throws Exception {
    }

    @Test
    public ResponseEntity<String> echo() throws Exception {
    }

    @Test
    public ResponseEntity<User> getUserById() throws Exception {
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(RestController.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
