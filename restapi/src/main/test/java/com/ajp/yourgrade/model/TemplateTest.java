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
public class TemplateTest {
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
    public double getGradeDeviation() throws Exception {
    }

    @Test
    public void setGradeDeviation() throws Exception {
    }

    @Test
    public boolean isCommentNeeded() throws Exception {
    }

    @Test
    public void setCommentNeeded() throws Exception {
    }

    @Test
    public User getUser() throws Exception {
    }

    @Test
    public void setUser() throws Exception {
    }

    @Test
    public Set<Group> getGroups() throws Exception {
    }

    @Test
    public void setGroups() throws Exception {
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Template.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
