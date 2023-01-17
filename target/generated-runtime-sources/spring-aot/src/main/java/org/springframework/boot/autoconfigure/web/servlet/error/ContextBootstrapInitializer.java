package org.springframework.boot.autoconfigure.web.servlet.error;

import org.springframework.aot.beans.factory.BeanDefinitionRegistrar;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.context.ApplicationContext;

public final class ContextBootstrapInitializer {
  public static void registerErrorMvcAutoConfiguration_DefaultErrorViewResolverConfiguration(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration$DefaultErrorViewResolverConfiguration", ErrorMvcAutoConfiguration.DefaultErrorViewResolverConfiguration.class).withConstructor(ApplicationContext.class, WebProperties.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> new ErrorMvcAutoConfiguration.DefaultErrorViewResolverConfiguration(attributes.get(0), attributes.get(1)))).register(beanFactory);
  }

  public static void registerErrorMvcAutoConfiguration_errorPageCustomizer(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("errorPageCustomizer", ErrorMvcAutoConfiguration.ErrorPageCustomizer.class).withFactoryMethod(ErrorMvcAutoConfiguration.class, "errorPageCustomizer", DispatcherServletPath.class)
        .instanceSupplier((instanceContext) -> instanceContext.create(beanFactory, (attributes) -> beanFactory.getBean(ErrorMvcAutoConfiguration.class).errorPageCustomizer(attributes.get(0)))).register(beanFactory);
  }

  public static void registerErrorMvcAutoConfiguration_preserveErrorControllerTargetClassPostProcessor(
      DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("preserveErrorControllerTargetClassPostProcessor", ErrorMvcAutoConfiguration.PreserveErrorControllerTargetClassPostProcessor.class).withFactoryMethod(ErrorMvcAutoConfiguration.class, "preserveErrorControllerTargetClassPostProcessor")
        .instanceSupplier(() -> ErrorMvcAutoConfiguration.preserveErrorControllerTargetClassPostProcessor()).register(beanFactory);
  }
}
