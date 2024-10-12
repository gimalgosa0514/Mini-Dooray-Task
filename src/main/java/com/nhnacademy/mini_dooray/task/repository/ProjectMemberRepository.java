package com.nhnacademy.mini_dooray.task.repository;

import com.nhnacademy.mini_dooray.task.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {

    List<ProjectMember> findByMemberId(String memberId);

    List<ProjectMember> findByProject_ProjectId(Long projectId);

    boolean existsByMemberId(String memberId);
}
