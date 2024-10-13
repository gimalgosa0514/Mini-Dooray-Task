package com.nhnacademy.mini_dooray.task.controller;

import com.nhnacademy.mini_dooray.task.domain.TagListResponse;
import com.nhnacademy.mini_dooray.task.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/project/{projectId}/tag")
public class TagController {

    private final TagService tagService;

    /**
     * 태그 목록 조회
     */
    @GetMapping
    public ResponseEntity<List<TagListResponse>> getTags(@PathVariable Long projectId) {
        List<TagListResponse> tagList = tagService.getTags(projectId);
        return ResponseEntity.status(OK).body(tagList);
    }
}
