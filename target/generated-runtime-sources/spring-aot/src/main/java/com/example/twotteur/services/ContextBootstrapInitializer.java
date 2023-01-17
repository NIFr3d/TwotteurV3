package com.example.twotteur.services;

import com.example.twotteur.repositories.AnswerAssoRepository;
import com.example.twotteur.repositories.FollowRepository;
import com.example.twotteur.repositories.LikeRepository;
import com.example.twotteur.repositories.MessageRepository;
import com.example.twotteur.repositories.RetwotAssoRepository;
import com.example.twotteur.repositories.RetwotRepository;
import com.example.twotteur.repositories.TwotRepository;
import com.example.twotteur.repositories.UserRepository;
import com.example.twotteur.repositories.WSTokenRepository;
import java.lang.reflect.Field;
import org.springframework.aot.beans.factory.BeanDefinitionRegistrar;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ReflectionUtils;

public final class ContextBootstrapInitializer {
  public static void registerFollowService(DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("followService", FollowService.class)
        .instanceSupplier((instanceContext) -> {
          FollowService bean = new FollowService();
          instanceContext.field("followRepository", FollowRepository.class)
              .invoke(beanFactory, (attributes) -> {
                Field followRepositoryField = ReflectionUtils.findField(FollowService.class, "followRepository", FollowRepository.class);
                ReflectionUtils.makeAccessible(followRepositoryField);
                ReflectionUtils.setField(followRepositoryField, bean, attributes.get(0));
              });
                  return bean;
                }).register(beanFactory);
          }

          public static void registerMessageService(DefaultListableBeanFactory beanFactory) {
            BeanDefinitionRegistrar.of("messageService", MessageService.class)
                .instanceSupplier((instanceContext) -> {
                  MessageService bean = new MessageService();
                  instanceContext.field("messageRepository", MessageRepository.class)
                      .invoke(beanFactory, (attributes) -> {
                        Field messageRepositoryField = ReflectionUtils.findField(MessageService.class, "messageRepository", MessageRepository.class);
                        ReflectionUtils.makeAccessible(messageRepositoryField);
                        ReflectionUtils.setField(messageRepositoryField, bean, attributes.get(0));
                      });
                          return bean;
                        }).register(beanFactory);
                  }

                  public static void registerRetwotService(DefaultListableBeanFactory beanFactory) {
                    BeanDefinitionRegistrar.of("retwotService", RetwotService.class)
                        .instanceSupplier((instanceContext) -> {
                          RetwotService bean = new RetwotService();
                          instanceContext.field("retwotRepository", RetwotRepository.class)
                              .invoke(beanFactory, (attributes) -> {
                                Field retwotRepositoryField = ReflectionUtils.findField(RetwotService.class, "retwotRepository", RetwotRepository.class);
                                ReflectionUtils.makeAccessible(retwotRepositoryField);
                                ReflectionUtils.setField(retwotRepositoryField, bean, attributes.get(0));
                              });
                                  instanceContext.field("userRepository", UserRepository.class)
                                      .invoke(beanFactory, (attributes) -> {
                                        Field userRepositoryField = ReflectionUtils.findField(RetwotService.class, "userRepository", UserRepository.class);
                                        ReflectionUtils.makeAccessible(userRepositoryField);
                                        ReflectionUtils.setField(userRepositoryField, bean, attributes.get(0));
                                      });
                                          instanceContext.field("twotRepository", TwotRepository.class)
                                              .invoke(beanFactory, (attributes) -> {
                                                Field twotRepositoryField = ReflectionUtils.findField(RetwotService.class, "twotRepository", TwotRepository.class);
                                                ReflectionUtils.makeAccessible(twotRepositoryField);
                                                ReflectionUtils.setField(twotRepositoryField, bean, attributes.get(0));
                                              });
                                                  return bean;
                                                }).register(beanFactory);
                                          }

                                          public static void registerTwotRetwotService(
                                              DefaultListableBeanFactory beanFactory) {
                                            BeanDefinitionRegistrar.of("twotRetwotService", TwotRetwotService.class)
                                                .instanceSupplier((instanceContext) -> {
                                                  TwotRetwotService bean = new TwotRetwotService();
                                                  instanceContext.field("twotRepository", TwotRepository.class)
                                                      .invoke(beanFactory, (attributes) -> {
                                                        Field twotRepositoryField = ReflectionUtils.findField(TwotRetwotService.class, "twotRepository", TwotRepository.class);
                                                        ReflectionUtils.makeAccessible(twotRepositoryField);
                                                        ReflectionUtils.setField(twotRepositoryField, bean, attributes.get(0));
                                                      });
                                                          instanceContext.field("retwotRepository", RetwotRepository.class)
                                                              .invoke(beanFactory, (attributes) -> {
                                                                Field retwotRepositoryField = ReflectionUtils.findField(TwotRetwotService.class, "retwotRepository", RetwotRepository.class);
                                                                ReflectionUtils.makeAccessible(retwotRepositoryField);
                                                                ReflectionUtils.setField(retwotRepositoryField, bean, attributes.get(0));
                                                              });
                                                                  return bean;
                                                                }).register(beanFactory);
                                                          }

                                                          public static void registerTwotService(
                                                              DefaultListableBeanFactory beanFactory) {
                                                            BeanDefinitionRegistrar.of("twotService", TwotService.class)
                                                                .instanceSupplier((instanceContext) -> {
                                                                  TwotService bean = new TwotService();
                                                                  instanceContext.field("twotRepository", TwotRepository.class)
                                                                      .invoke(beanFactory, (attributes) -> {
                                                                        Field twotRepositoryField = ReflectionUtils.findField(TwotService.class, "twotRepository", TwotRepository.class);
                                                                        ReflectionUtils.makeAccessible(twotRepositoryField);
                                                                        ReflectionUtils.setField(twotRepositoryField, bean, attributes.get(0));
                                                                      });
                                                                          instanceContext.field("likeRepository", LikeRepository.class)
                                                                              .invoke(beanFactory, (attributes) -> {
                                                                                Field likeRepositoryField = ReflectionUtils.findField(TwotService.class, "likeRepository", LikeRepository.class);
                                                                                ReflectionUtils.makeAccessible(likeRepositoryField);
                                                                                ReflectionUtils.setField(likeRepositoryField, bean, attributes.get(0));
                                                                              });
                                                                                  instanceContext.field("userRepository", UserRepository.class)
                                                                                      .invoke(beanFactory, (attributes) -> {
                                                                                        Field userRepositoryField = ReflectionUtils.findField(TwotService.class, "userRepository", UserRepository.class);
                                                                                        ReflectionUtils.makeAccessible(userRepositoryField);
                                                                                        ReflectionUtils.setField(userRepositoryField, bean, attributes.get(0));
                                                                                      });
                                                                                          instanceContext.field("answerAssoRepository", AnswerAssoRepository.class)
                                                                                              .invoke(beanFactory, (attributes) -> {
                                                                                                Field answerAssoRepositoryField = ReflectionUtils.findField(TwotService.class, "answerAssoRepository", AnswerAssoRepository.class);
                                                                                                ReflectionUtils.makeAccessible(answerAssoRepositoryField);
                                                                                                ReflectionUtils.setField(answerAssoRepositoryField, bean, attributes.get(0));
                                                                                              });
                                                                                                  instanceContext.field("retwotRepository", RetwotRepository.class)
                                                                                                      .invoke(beanFactory, (attributes) -> {
                                                                                                        Field retwotRepositoryField = ReflectionUtils.findField(TwotService.class, "retwotRepository", RetwotRepository.class);
                                                                                                        ReflectionUtils.makeAccessible(retwotRepositoryField);
                                                                                                        ReflectionUtils.setField(retwotRepositoryField, bean, attributes.get(0));
                                                                                                      });
                                                                                                          instanceContext.field("retwotAssoRepository", RetwotAssoRepository.class)
                                                                                                              .invoke(beanFactory, (attributes) -> {
                                                                                                                Field retwotAssoRepositoryField = ReflectionUtils.findField(TwotService.class, "retwotAssoRepository", RetwotAssoRepository.class);
                                                                                                                ReflectionUtils.makeAccessible(retwotAssoRepositoryField);
                                                                                                                ReflectionUtils.setField(retwotAssoRepositoryField, bean, attributes.get(0));
                                                                                                              });
                                                                                                                  return bean;
                                                                                                                }).register(beanFactory);
                                                                                                          }

                                                                                                          public static void registerUserService(
                                                                                                              DefaultListableBeanFactory beanFactory) {
                                                                                                            BeanDefinitionRegistrar.of("userService", UserService.class)
                                                                                                                .instanceSupplier((instanceContext) -> {
                                                                                                                  UserService bean = new UserService();
                                                                                                                  instanceContext.field("userRepository", UserRepository.class)
                                                                                                                      .invoke(beanFactory, (attributes) -> {
                                                                                                                        Field userRepositoryField = ReflectionUtils.findField(UserService.class, "userRepository", UserRepository.class);
                                                                                                                        ReflectionUtils.makeAccessible(userRepositoryField);
                                                                                                                        ReflectionUtils.setField(userRepositoryField, bean, attributes.get(0));
                                                                                                                      });
                                                                                                                          return bean;
                                                                                                                        }).register(beanFactory);
                                                                                                                  }

                                                                                                                  public static void registerWSTokenService(
                                                                                                                      DefaultListableBeanFactory beanFactory) {
                                                                                                                    BeanDefinitionRegistrar.of("WSTokenService", WSTokenService.class)
                                                                                                                        .instanceSupplier((instanceContext) -> {
                                                                                                                          WSTokenService bean = new WSTokenService();
                                                                                                                          instanceContext.field("wsTokenRepository", WSTokenRepository.class)
                                                                                                                              .invoke(beanFactory, (attributes) -> {
                                                                                                                                Field wsTokenRepositoryField = ReflectionUtils.findField(WSTokenService.class, "wsTokenRepository", WSTokenRepository.class);
                                                                                                                                ReflectionUtils.makeAccessible(wsTokenRepositoryField);
                                                                                                                                ReflectionUtils.setField(wsTokenRepositoryField, bean, attributes.get(0));
                                                                                                                              });
                                                                                                                                  return bean;
                                                                                                                                }).register(beanFactory);
                                                                                                                          }
                                                                                                                        }
