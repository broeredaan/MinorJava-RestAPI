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
public class RatingTest {
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
    public double getGrade() throws Exception {
    }

    @Test
    public void setGrade() throws Exception {
    }

    @Test
    public String getComment() throws Exception {
    }

    @Test
    public void setComment() throws Exception {
    }

    @Test
    public GroupMember getGroupMember() throws Exception {
    }

    @Test
    public void setGroupMember() throws Exception {
    }

    @Test
    public GroupMember getRatedMember() throws Exception {
    }

    @Test
    public void setRatedMember() throws Exception {
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Rating.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
