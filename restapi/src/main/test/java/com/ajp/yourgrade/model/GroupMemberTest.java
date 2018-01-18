package com.ajp.yourgrade.model;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Set;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class GroupMemberTest {
    @Test
    public String toString() throws Exception {
    }

    @Test
    public int getId() throws Exception {
    }

    @Test
    public void setId() throws Exception {
    }

    @Test
    public String getName() throws Exception {
    }

    @Test
    public void setName() throws Exception {
    }

    @Test
    public String getEmail() throws Exception {
    }

    @Test
    public void setEmail() throws Exception {
    }

    @Test
    public String getToken() throws Exception {
    }

    @Test
    public void setToken() throws Exception {
    }

    @Test
    public boolean isHasSubmitted() throws Exception {
    }

    @Test
    public void setHasSubmitted() throws Exception {
    }

    @Test
    public Group getGroup() throws Exception {
    }

    @Test
    public void setGroup() throws Exception {
    }

    @Test
    public Set<Rating> getRatings() throws Exception {
    }

    @Test
    public void setRatings() throws Exception {
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(GroupMember.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
