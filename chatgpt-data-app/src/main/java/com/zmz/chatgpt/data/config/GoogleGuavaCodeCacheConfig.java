package com.zmz.chatgpt.data.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.eventbus.EventBus;
import com.zmz.chatgpt.data.trigger.mq.OrderPaySuccessListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class GoogleGuavaCodeCacheConfig {

    @Bean(name = "codeCache")
    public Cache<String, String> codeCache() {
        return CacheBuilder.newBuilder()
                .expireAfterWrite(3, TimeUnit.MINUTES)
                .build();
    }

    @Bean(name = "visitCache")
    public Cache<String, Integer> visitCache() {
        return CacheBuilder.newBuilder()
                .expireAfterWrite(1, TimeUnit.DAYS)
                .build();
    }

    /**
     * event bus 的消息队列是放在内存中的 重启丢失
     */
    @Bean
    public EventBus eventBusListener(OrderPaySuccessListener listener){
        EventBus eventBus = new EventBus();
        eventBus.register(listener);
        return eventBus;
    }

}
