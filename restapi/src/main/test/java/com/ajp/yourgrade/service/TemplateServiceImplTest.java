package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.Template;
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
public class TemplateServiceImplTest {
    @Test
    public void addTemplate() throws Exception {
    }

    @Test
    public void deleteTemplate() throws Exception {
    }

    @Test
    public void getTemplateById() throws Exception {
    }

    @Test
    public void getTemplateByUser() throws Exception {
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(TemplateServiceImpl.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
