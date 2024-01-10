package com.zmz.chatgpt.data.domain.openai.service;

import com.zmz.chatgpt.data.domain.openai.model.aggregates.ChatProcessAggregate;
import com.zmz.chatgpt.data.domain.openai.model.entity.RuleLogicEntity;
import com.zmz.chatgpt.data.domain.openai.model.valobj.LogicCheckTypeVO;
import com.zmz.chatgpt.data.domain.openai.service.logic.ChatGLMService;
import com.zmz.chatgpt.data.domain.openai.service.logic.ChatGPTService;
import com.zmz.chatgpt.data.domain.openai.service.logic.OpenAiGroupService;
import com.zmz.chatgpt.data.domain.openai.service.rule.factory.DefaultLogicFactory;
import com.zmz.chatgpt.data.types.common.Constants;
import com.zmz.chatgpt.data.types.enums.OpenAiChannel;
import com.zmz.chatgpt.data.types.exception.ChatGPTException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public abstract class AbstractChatService implements IChatService {

    private final Map<OpenAiChannel, OpenAiGroupService> openAiGroup = new HashMap<>();

    public AbstractChatService(ChatGPTService chatGPTService, ChatGLMService chatGLMService) {
        openAiGroup.put(OpenAiChannel.ChatGPT, chatGPTService);
        openAiGroup.put(OpenAiChannel.ChatGLM, chatGLMService);
    }

//    @Resource
//    private IOpenAiRepository openAiRepository;

    @Override
    public ResponseBodyEmitter completions(ResponseBodyEmitter emitter, ChatProcessAggregate chatProcess) {
//        ResponseBodyEmitter emitter = new ResponseBodyEmitter(3 * 60 * 1000L);
        try {
            // 1. 请求应答
            emitter.onCompletion(() -> {
                log.info("流式问答请求完成，使用模型：{}", chatProcess.getModel());
            });
            emitter.onError(throwable -> log.error("流式问答请求疫情，使用模型：{}", chatProcess.getModel(), throwable));

            // 2. 规则过滤
            RuleLogicEntity<ChatProcessAggregate> ruleLogicEntity = this.doCheckLogic(chatProcess,
                    DefaultLogicFactory.LogicModel.ACCESS_LIMIT.getCode(),
                    DefaultLogicFactory.LogicModel.SENSITIVE_WORD.getCode());

            if (!LogicCheckTypeVO.SUCCESS.equals(ruleLogicEntity.getType())) {
                emitter.send(ruleLogicEntity.getInfo());
                emitter.complete();
                return emitter;
            }

            // 4. 应答处理 【ChatGPT、ChatGLM 策略模式】
            openAiGroup.get(chatProcess.getChannel()).doMessageResponse(ruleLogicEntity.getData(), emitter);
        } catch (Exception e) {
            throw new ChatGPTException(Constants.ResponseCode.UN_ERROR.getCode(), Constants.ResponseCode.UN_ERROR.getInfo());
        }

        // 5. 返回结果
        return emitter;
    }

    protected abstract RuleLogicEntity<ChatProcessAggregate> doCheckLogic(ChatProcessAggregate chatProcess, String... logics) throws Exception;

}
