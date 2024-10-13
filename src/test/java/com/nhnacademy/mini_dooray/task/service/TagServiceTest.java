package com.nhnacademy.mini_dooray.task.service;

import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.mini_dooray.task.domain.TagListResponse;
import com.nhnacademy.mini_dooray.task.entity.Project;
import com.nhnacademy.mini_dooray.task.entity.ProjectStatus;
import com.nhnacademy.mini_dooray.task.entity.Tag;
import com.nhnacademy.mini_dooray.task.exception.NoSuchProjectFoundException;
import com.nhnacademy.mini_dooray.task.exception.TagDuplicateException;
import com.nhnacademy.mini_dooray.task.exception.TagNotFoundException;
import com.nhnacademy.mini_dooray.task.repository.ProjectRepository;
import com.nhnacademy.mini_dooray.task.repository.TagRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class TagServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private TagService tagService;

    private Project project;
    private Tag tag1, tag2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        project = new Project("Test Project", ProjectStatus.ACTIVE, "manager1");
        tag1 = new Tag("Tag1", project);
        tag2 = new Tag("Tag2", project);
    }

    @Test
    void getTags_shouldReturnListOfTagListResponse() {
        when(tagRepository.existsByProject_ProjectId(project.getProjectId())).thenReturn(true);
        when(tagRepository.findByProject_ProjectId(project.getProjectId())).thenReturn(Arrays.asList(tag1, tag2));

        List<TagListResponse> tags = tagService.getTags(project.getProjectId());

        assertEquals(2, tags.size());
        assertEquals("Tag1", tags.get(0).getTagName());
        assertEquals("Tag2", tags.get(1).getTagName());
    }

    @Test
    void getTags_shouldThrowTagNotFoundException() {
        when(tagRepository.existsByProject_ProjectId(project.getProjectId())).thenReturn(false);

        assertThrows(TagNotFoundException.class, () -> {
            tagService.getTags(project.getProjectId());
        });
    }

    @Test
    void addTag_shouldSaveTag() {
        when(projectRepository.findById(project.getProjectId())).thenReturn(Optional.of(project));
        when(tagRepository.existsByTagNameAndProject("Tag1", project)).thenReturn(false);

        tagService.addTag(project.getProjectId(), "Tag1");

        verify(tagRepository, times(1)).save(any(Tag.class));
    }

    @Test
    void addTag_shouldThrowNoSuchProjectFoundException() {
        when(projectRepository.findById(project.getProjectId())).thenReturn(Optional.empty());

        assertThrows(NoSuchProjectFoundException.class, () -> {
            tagService.addTag(project.getProjectId(), "Tag1");
        });
    }

    @Test
    void addTag_shouldThrowTagDuplicateException() {
        when(projectRepository.findById(project.getProjectId())).thenReturn(Optional.of(project));
        when(tagRepository.existsByTagNameAndProject("Tag1", project)).thenReturn(true);

        assertThrows(TagDuplicateException.class, () -> {
            tagService.addTag(project.getProjectId(), "Tag1");
        });
    }

    @Test
    void deleteTag_shouldDeleteTag() {
        when(tagRepository.findById(tag1.getTagId())).thenReturn(Optional.of(tag1));

        tagService.deleteTag(tag1.getTagId());

        verify(tagRepository, times(1)).delete(tag1);
    }

    @Test
    void deleteTag_shouldThrowTagNotFoundException() {
        when(tagRepository.findById(tag1.getTagId())).thenReturn(Optional.empty());

        assertThrows(TagNotFoundException.class, () -> {
            tagService.deleteTag(tag1.getTagId());
        });
    }
}
