package com.zmz.chatgpt.data.domain.openai.service;

import cn.bugstack.chatgpt.common.Constants;
import cn.bugstack.chatgpt.domain.chat.ChatChoice;
import cn.bugstack.chatgpt.domain.chat.ChatCompletionRequest;
import cn.bugstack.chatgpt.domain.chat.ChatCompletionResponse;
import cn.bugstack.chatgpt.domain.chat.Message;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.zmz.chatgpt.data.domain.openai.model.aggregates.ChatProcessAggregate;
import com.zmz.chatgpt.data.domain.openai.service.logic.ChatGLMService;
import com.zmz.chatgpt.data.domain.openai.service.logic.ChatGPTService;
import com.zmz.chatgpt.data.types.exception.ChatGPTException;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatService extends AbstractChatService {

//    @Resource
//    private DefaultLogicFactory logicFactory;

    public ChatService(ChatGPTService chatGPTService, ChatGLMService chatGLMService) {
        super(chatGPTService, chatGLMService);
    }

//    @Override
//    protected RuleLogicEntity<ChatProcessAggregate> doCheckLogic(ChatProcessAggregate chatProcess, UserAccountQuotaEntity userAccountQuotaEntity, String... logics) throws Exception {
//        Map<String, ILogicFilter<UserAccountQuotaEntity>> logicFilterMap = logicFactory.openLogicFilter();
//        RuleLogicEntity<ChatProcessAggregate> entity = null;
//        for (String code : logics) {
//            if (DefaultLogicFactory.LogicModel.NULL.getCode().equals(code)) continue;
//            entity = logicFilterMap.get(code).filter(chatProcess, userAccountQuotaEntity);
//            if (!LogicCheckTypeVO.SUCCESS.equals(entity.getType())) return entity;
//        }
//        return entity != null ? entity : RuleLogicEntity.<ChatProcessAggregate>builder()
//                .type(LogicCheckTypeVO.SUCCESS).data(chatProcess).build();
//    }

}
