package com.nhnacademy.mini_dooray.task.service;

import com.nhnacademy.mini_dooray.task.domain.TagListResponse;
import com.nhnacademy.mini_dooray.task.entity.Project;
import com.nhnacademy.mini_dooray.task.entity.Tag;
import com.nhnacademy.mini_dooray.task.exception.NoSuchProjectFoundException;
import com.nhnacademy.mini_dooray.task.exception.TagDuplicateException;
import com.nhnacademy.mini_dooray.task.exception.TagNotFoundException;
import com.nhnacademy.mini_dooray.task.repository.ProjectRepository;
import com.nhnacademy.mini_dooray.task.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TagService {

    private final ProjectRepository projectRepository;
    private final TagRepository tagRepository;

    @Transactional(readOnly = true)
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

    public void addTag(Long projectId, String tagName) {
        Project foundProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchProjectFoundException("project not found"));

        if (tagRepository.existsByTagNameAndProject(tagName, foundProject)) {
            throw new TagDuplicateException("duplicate tag exists");
        }
        tagRepository.save(new Tag(tagName, foundProject));
    }

    public void deleteTag(Long tagId) {
        Tag foundTag = tagRepository.findById(tagId)
                .orElseThrow(() -> new TagNotFoundException("tag not found"));
        tagRepository.delete(foundTag);
    }
}
