package com.nhnacademy.mini_dooray.task.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProjectMemberRepositoryTest {

    @Test
    @DisplayName("멤버 아이디로 해당 멤버가 속한 프로젝트 조회")
    public void findByMember_MemberId() {

    }

}