package com.nhnacademy.mini_dooray.task.service;

import com.nhnacademy.mini_dooray.task.domain.TagListResponse;
import com.nhnacademy.mini_dooray.task.entity.Tag;
import com.nhnacademy.mini_dooray.task.exception.TagNotFoundException;
import com.nhnacademy.mini_dooray.task.repository.ProjectRepository;
import com.nhnacademy.mini_dooray.task.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {

    private final ProjectRepository projectRepository;
    private final TagRepository tagRepository;

    public List<TagListResponse> getTags(Long projectId) {
        if (tagRepository.existsByProject_ProjectId(projectId)) {
            List<Tag> foundTags = tagRepository.findByProject_ProjectId(projectId);

            List<TagListResponse> tagList = foundTags.stream()
                    .map(tag -> new TagListResponse(tag.getTagId(), tag.getTagName()))
                    .collect(Collectors.toList());

            return tagList;
        } else {
            throw new TagNotFoundException("tag not found");
        }
    }
}
