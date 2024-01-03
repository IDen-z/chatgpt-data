package com.zmz.chatgpt.data.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description ChatGLMSDKConfigProperties
 * @create 2023-10-14 16:00
 */
@Data
@ConfigurationProperties(prefix = "chatglm.sdk.config", ignoreInvalidFields = true)
public class ChatGLMSDKConfigProperties {

    /** 状态；open = 开启、close 关闭 */
    private boolean enabled;
    /** 转发地址 */
    private String apiHost;
    /** 可以申请 sk-*** */
    private String apiSecretKey;

}