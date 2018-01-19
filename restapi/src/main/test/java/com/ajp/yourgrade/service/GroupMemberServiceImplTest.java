package com.ajp.yourgrade.service;

import com.ajp.yourgrade.model.Group;
import com.ajp.yourgrade.model.GroupMember;
import com.ajp.yourgrade.model.Template;
import com.ajp.yourgrade.model.User;
import com.ajp.yourgrade.persistence.GroupMemberRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GroupMemberServiceImplTest {
    private GroupMemberRepository testGMRepostitory;

    private GroupMemberServiceImpl testGMServiceImpl = new GroupMemberServiceImpl(testGMRepostitory);
    @Test
    public void getMembersByGroup() throws Exception {
        List<GroupMember> actual = testGMServiceImpl.getMembersByGroup(new Group("TestBestHackersEvahh", new Date(2018, 01, 01, 00,00, 00), new Date(2018, 01, 31, 00,00, 00),8, new Template("CEH",2, true,
                new User("TestDummy1", "TestDummy1@gmail.com", true, "Test", "English"))));
        List<GroupMember> expected = Arrays.asList(new GroupMember("TestSchoolier", "TestSchoolier@gmail.com", "testtest", false, new Group("TestBestHackersEvahh", new Date(2018, 01, 01, 00,00, 00), new Date(2018, 01, 31, 00,00, 00),8, new Template("CEH",2, true,
                new User("TestDummy1", "TestDummy1@gmail.com", true, "Test", "English")))));

        assertThat(actual, is(expected));
    }

    @Test
    public void getMembersById(){
    }

    public void getMemberByToken(){

    }
}
