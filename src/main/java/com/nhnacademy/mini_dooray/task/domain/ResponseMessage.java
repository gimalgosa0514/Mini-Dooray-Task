package com.nhnacademy.mini_dooray.task.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@RequiredArgsConstructor
public class ResponseMessage {
    @JsonProperty("message")
    private final String message;
}
