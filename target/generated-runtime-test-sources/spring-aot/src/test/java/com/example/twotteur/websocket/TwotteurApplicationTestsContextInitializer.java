package com.example.twotteur.websocket;

import com.example.twotteur.services.UserService;
import com.example.twotteur.services.WSTokenService;
import java.lang.reflect.Field;
import org.springframework.aot.beans.factory.BeanDefinitionRegistrar;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ReflectionUtils;

public final class TwotteurApplicationTestsContextInitializer {
  public static void registerWebSocketConfig(DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("webSocketConfig", WebSocketConfig.class)
        .instanceSupplier((instanceContext) -> {
          WebSocketConfig bean = new WebSocketConfig();
          instanceContext.field("wsTokenService", WSTokenService.class)
              .invoke(beanFactory, (attributes) -> {
                Field wsTokenServiceField = ReflectionUtils.findField(WebSocketConfig.class, "wsTokenService", WSTokenService.class);
                ReflectionUtils.makeAccessible(wsTokenServiceField);
                ReflectionUtils.setField(wsTokenServiceField, bean, attributes.get(0));
              });
                  instanceContext.field("userService", UserService.class)
                      .invoke(beanFactory, (attributes) -> {
                        Field userServiceField = ReflectionUtils.findField(WebSocketConfig.class, "userService", UserService.class);
                        ReflectionUtils.makeAccessible(userServiceField);
                        ReflectionUtils.setField(userServiceField, bean, attributes.get(0));
                      });
                          return bean;
                        }).register(beanFactory);
                  }
                }
