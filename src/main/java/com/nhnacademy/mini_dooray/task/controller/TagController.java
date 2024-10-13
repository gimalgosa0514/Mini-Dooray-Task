package com.nhnacademy.mini_dooray.task.controller;

import com.nhnacademy.mini_dooray.task.domain.ResponseMessage;
import com.nhnacademy.mini_dooray.task.domain.TagCreateRequest;
import com.nhnacademy.mini_dooray.task.domain.TagListResponse;
import com.nhnacademy.mini_dooray.task.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 태그 추가
     */
    @PostMapping
    public ResponseEntity<ResponseMessage> addTag(
            @PathVariable Long projectId, @RequestBody TagCreateRequest request) {
        tagService.addTag(projectId, request.getName());
        ResponseMessage responseMessage = new ResponseMessage("생성 성공");

        return ResponseEntity.status(CREATED).body(responseMessage);
    }

    /**
     * 태그 삭제
     */
    @DeleteMapping("/{tagId}")
    public ResponseEntity<ResponseMessage> deleteTag(@PathVariable Long tagId) {
        tagService.deleteTag(tagId);
        ResponseMessage responseMessage = new ResponseMessage("삭제 성공");

        return ResponseEntity.status(OK).body(responseMessage);
    }
}
