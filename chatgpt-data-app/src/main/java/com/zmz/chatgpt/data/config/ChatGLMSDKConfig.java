package com.zmz.chatgpt.data.config;
import com.zmz.chatgpt.session.OpenAiSession;
import com.zmz.chatgpt.session.OpenAiSessionFactory;
import com.zmz.chatgpt.session.defaults.DefaultOpenAiSessionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description ChatGLMSDKConfig 工厂配置开启
 * @create 2023-10-14 16:00
 */
@Configuration
@EnableConfigurationProperties(ChatGLMSDKConfigProperties.class)
public class ChatGLMSDKConfig {

    @Bean(name = "chatGlMOpenAiSession")
    @ConditionalOnProperty(value = "chatglm.sdk.config.enabled", havingValue = "true")
    public OpenAiSession openAiSession(ChatGLMSDKConfigProperties properties) {
        // 1. 配置文件
        com.zmz.chatgpt.session.Configuration configuration = new com.zmz.chatgpt.session.Configuration();
        configuration.setApiHost(properties.getApiHost());
        configuration.setApiSecretKey(properties.getApiSecretKey());

        // 2. 会话工厂
        OpenAiSessionFactory factory = new DefaultOpenAiSessionFactory(configuration);

        // 3. 开启会话
        return factory.openSession();
    }

}
