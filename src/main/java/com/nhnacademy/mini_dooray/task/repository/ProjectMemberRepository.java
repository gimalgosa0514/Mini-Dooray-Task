package com.nhnacademy.mini_dooray.task.repository;

import com.nhnacademy.mini_dooray.task.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {

    List<ProjectMember> findByMember_MemberId(String memberId);

    List<ProjectMember> findByProject_ProjectId(Long projectId);

    boolean existsByMember_MemberId(String memberId);
}
