package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.Group;
import com.ajp.yourgrade.model.GroupMember;
import com.ajp.yourgrade.model.Template;
import com.ajp.yourgrade.model.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class GroupMemberServiceImplTest {
    @Test
    public void createMember() throws Exception {
        GroupMemberServiceImpl.createMember("Testdummy1", "Testdummy1@gmail.com","Token1",false, new Group("TestGroup1", new Date(2018, 01, 18, 11, 01, 11), new Date(2018, 01, 25, 12, 00, 00), 20,
                null));

    }

    @Test
    public void deleteMember() throws Exception {
    }

    @Test
    public void getMembersByGroup() throws Exception {

        GroupMemberServiceImpl.getMembersByGroup(new Group("TestBestHackersEvahh", new Date(2018, 01, 01, 00,00, 00), new Date(2018, 01, 31, 00,00, 00),8, new Template("CEH",2, true,
                new User("TestDummy1", "TestDummy1@gmail.com", true, "Test", "English"))));
        
    }

    @Test
    public void getMembersById(){
    }

    public void getMemberByToken(){

    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(GroupMemberServiceImpl.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
