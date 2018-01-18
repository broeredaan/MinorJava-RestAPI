package com.ajp.yourgrade.model;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class UserTest {
    @Test
    public void toString() throws Exception {
    }

    @Test
    public void getId() throws Exception {
    }

    @Test
    public void setId() throws Exception {
    }

    @Test
    public void getName() throws Exception {
    }

    @Test
    public void setName() throws Exception {
    }

    @Test
    public void getEmail() throws Exception {
    }

    @Test
    public void setEmail() throws Exception {
    }

    @Test
    public void isAdmin() throws Exception {
    }

    @Test
    public void setAdmin() throws Exception {
    }

    @Test
    public void getPassword() throws Exception {
    }

    @Test
    public void setPassword() throws Exception {
    }

    @Test
    public void getLanguage() throws Exception {
    }

    @Test
    public void setLanguage() throws Exception {
    }

    @Test
    public void getTemplates() throws Exception {
    }

    @Test
    public void setTemplates() throws Exception {
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(User.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
