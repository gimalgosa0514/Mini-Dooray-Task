package com.nhnacademy.mini_dooray.task.repository;

import com.nhnacademy.mini_dooray.task.entity.ProjectMember;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ProjectMemberRepositoryTest {

    @Autowired
    private ProjectMemberRepository projectMemberRepository;

    @Test
    @DisplayName("멤버 아이디로 해당 멤버가 속한 프로젝트 리스트 조회")
    @Sql("project-member-repository-test.sql")
    public void findByMember_MemberId() {

        List<ProjectMember> projectMembersByAaa = projectMemberRepository.findByMember_MemberId("aaa");
        List<ProjectMember> projectMembersByDdd = projectMemberRepository.findByMember_MemberId("ddd");

        assertThat(projectMembersByAaa).hasSize(3);
        assertThat(projectMembersByDdd).hasSize(3);
    }

    @Test
    @DisplayName("프로젝트 아이디로 해당 프로젝트에 속한 멤버 리스트 조회")
    @Sql("project-member-repository-test.sql")
    public void findByProject_ProjectId() {

        List<ProjectMember> projectMembersBy4 = projectMemberRepository.findByProject_ProjectId(4L);
        List<ProjectMember> projectMembersBy5 = projectMemberRepository.findByProject_ProjectId(5L);

        assertThat(projectMembersBy4).hasSize(2);
        assertThat(projectMembersBy5).hasSize(1);
    }
}