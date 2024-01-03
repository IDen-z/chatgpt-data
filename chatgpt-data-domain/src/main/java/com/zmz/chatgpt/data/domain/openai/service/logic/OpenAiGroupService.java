package com.zmz.chatgpt.data.domain.openai.service.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zmz.chatgpt.data.domain.openai.model.aggregates.ChatProcessAggregate;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

public interface OpenAiGroupService {

    void doMessageResponse(ChatProcessAggregate chatProcess, ResponseBodyEmitter emitter) throws JsonProcessingException;

}
