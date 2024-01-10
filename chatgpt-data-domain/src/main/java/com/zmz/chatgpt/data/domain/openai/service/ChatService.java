package com.zmz.chatgpt.data.domain.openai.service;

import com.zmz.chatgpt.data.domain.openai.model.aggregates.ChatProcessAggregate;
import com.zmz.chatgpt.data.domain.openai.model.entity.RuleLogicEntity;
import com.zmz.chatgpt.data.domain.openai.model.valobj.LogicCheckTypeVO;
import com.zmz.chatgpt.data.domain.openai.service.logic.ChatGLMService;
import com.zmz.chatgpt.data.domain.openai.service.logic.ChatGPTService;
import com.zmz.chatgpt.data.domain.openai.service.rule.ILogicFilter;
import com.zmz.chatgpt.data.domain.openai.service.rule.factory.DefaultLogicFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class ChatService extends AbstractChatService {

    @Resource
    private DefaultLogicFactory logicFactory;

    @Autowired
    public ChatService(ChatGPTService chatGPTService, ChatGLMService chatGLMService) {
        super(chatGPTService, chatGLMService);
    }

    @Override
    protected RuleLogicEntity<ChatProcessAggregate> doCheckLogic(ChatProcessAggregate chatProcess, String... logics) throws Exception {
        Map<String, ILogicFilter> logicFilterMap = logicFactory.openLogicFilter();
        RuleLogicEntity<ChatProcessAggregate> entity = null;
        for (String code : logics) {
            entity = logicFilterMap.get(code).filter(chatProcess);
            if (!LogicCheckTypeVO.SUCCESS.equals(entity.getType())) return entity;
        }
        return entity != null ? entity : RuleLogicEntity.<ChatProcessAggregate>builder()
                .type(LogicCheckTypeVO.SUCCESS).data(chatProcess).build();
    }
}
