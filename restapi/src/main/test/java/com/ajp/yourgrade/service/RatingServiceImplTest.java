package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.Rating;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class RatingServiceImplTest {
    @Test
    public void addRating() throws Exception {
    }

    @Test
    public void deleteRating() throws Exception {
    }

    @Test
    public Rating getById() throws Exception {
    }

    @Test
    public List<Rating> getByGroupMember() throws Exception {
    }

    @Test
    public List<Rating> getByRatedMember() throws Exception {
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(RatingServiceImpl.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
