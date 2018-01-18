package com.ajp.yourgrade.model;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class GroupTest {
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
    public Date getCreationDate() throws Exception {
    }

    @Test
    public void setCreationDate() throws Exception {
    }

    @Test
    public Date getDeadline() throws Exception {
    }

    @Test
    public void setDeadline() throws Exception {
    }

    @Test
    public double getGroupGrade() throws Exception {
    }

    @Test
    public void setGroupGrade() throws Exception {
    }

    @Test
    public Template getTemplate() throws Exception {
    }

    @Test
    public void setTemplate() throws Exception {
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Group.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
