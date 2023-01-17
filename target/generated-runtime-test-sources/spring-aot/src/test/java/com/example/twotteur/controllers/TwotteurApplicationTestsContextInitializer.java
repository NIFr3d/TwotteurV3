package com.example.twotteur.controllers;

import com.example.twotteur.services.FollowService;
import com.example.twotteur.services.MessageService;
import com.example.twotteur.services.RetwotService;
import com.example.twotteur.services.TwotRetwotService;
import com.example.twotteur.services.TwotService;
import com.example.twotteur.services.UserService;
import com.example.twotteur.services.WSTokenService;
import java.lang.reflect.Field;
import org.springframework.aot.beans.factory.BeanDefinitionRegistrar;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ReflectionUtils;

public final class TwotteurApplicationTestsContextInitializer {
  public static void registerAuthController(DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("authController", AuthController.class)
        .instanceSupplier((instanceContext) -> {
          AuthController bean = new AuthController();
          instanceContext.field("userService", UserService.class)
              .invoke(beanFactory, (attributes) -> {
                Field userServiceField = ReflectionUtils.findField(AuthController.class, "userService", UserService.class);
                ReflectionUtils.makeAccessible(userServiceField);
                ReflectionUtils.setField(userServiceField, bean, attributes.get(0));
              });
                  instanceContext.field("tokenService", WSTokenService.class)
                      .invoke(beanFactory, (attributes) -> {
                        Field tokenServiceField = ReflectionUtils.findField(AuthController.class, "tokenService", WSTokenService.class);
                        ReflectionUtils.makeAccessible(tokenServiceField);
                        ReflectionUtils.setField(tokenServiceField, bean, attributes.get(0));
                      });
                          return bean;
                        }).register(beanFactory);
                  }

                  public static void registerFollowController(
                      DefaultListableBeanFactory beanFactory) {
                    BeanDefinitionRegistrar.of("followController", FollowController.class)
                        .instanceSupplier((instanceContext) -> {
                          FollowController bean = new FollowController();
                          instanceContext.field("followService", FollowService.class)
                              .invoke(beanFactory, (attributes) -> {
                                Field followServiceField = ReflectionUtils.findField(FollowController.class, "followService", FollowService.class);
                                ReflectionUtils.makeAccessible(followServiceField);
                                ReflectionUtils.setField(followServiceField, bean, attributes.get(0));
                              });
                                  instanceContext.field("userService", UserService.class)
                                      .invoke(beanFactory, (attributes) -> {
                                        Field userServiceField = ReflectionUtils.findField(FollowController.class, "userService", UserService.class);
                                        ReflectionUtils.makeAccessible(userServiceField);
                                        ReflectionUtils.setField(userServiceField, bean, attributes.get(0));
                                      });
                                          return bean;
                                        }).register(beanFactory);
                                  }

                                  public static void registerMessageController(
                                      DefaultListableBeanFactory beanFactory) {
                                    BeanDefinitionRegistrar.of("messageController", MessageController.class)
                                        .instanceSupplier((instanceContext) -> {
                                          MessageController bean = new MessageController();
                                          instanceContext.field("userService", UserService.class)
                                              .invoke(beanFactory, (attributes) -> {
                                                Field userServiceField = ReflectionUtils.findField(MessageController.class, "userService", UserService.class);
                                                ReflectionUtils.makeAccessible(userServiceField);
                                                ReflectionUtils.setField(userServiceField, bean, attributes.get(0));
                                              });
                                                  instanceContext.field("messageService", MessageService.class)
                                                      .invoke(beanFactory, (attributes) -> {
                                                        Field messageServiceField = ReflectionUtils.findField(MessageController.class, "messageService", MessageService.class);
                                                        ReflectionUtils.makeAccessible(messageServiceField);
                                                        ReflectionUtils.setField(messageServiceField, bean, attributes.get(0));
                                                      });
                                                          instanceContext.field("followService", FollowService.class)
                                                              .invoke(beanFactory, (attributes) -> {
                                                                Field followServiceField = ReflectionUtils.findField(MessageController.class, "followService", FollowService.class);
                                                                ReflectionUtils.makeAccessible(followServiceField);
                                                                ReflectionUtils.setField(followServiceField, bean, attributes.get(0));
                                                              });
                                                                  return bean;
                                                                }).register(beanFactory);
                                                          }

                                                          public static void registerMessageRestController(
                                                              DefaultListableBeanFactory beanFactory) {
                                                            BeanDefinitionRegistrar.of("messageRestController", MessageRestController.class)
                                                                .instanceSupplier((instanceContext) -> {
                                                                  MessageRestController bean = new MessageRestController();
                                                                  instanceContext.field("messageService", MessageService.class)
                                                                      .invoke(beanFactory, (attributes) -> {
                                                                        Field messageServiceField = ReflectionUtils.findField(MessageRestController.class, "messageService", MessageService.class);
                                                                        ReflectionUtils.makeAccessible(messageServiceField);
                                                                        ReflectionUtils.setField(messageServiceField, bean, attributes.get(0));
                                                                      });
                                                                          instanceContext.field("userService", UserService.class)
                                                                              .invoke(beanFactory, (attributes) -> {
                                                                                Field userServiceField = ReflectionUtils.findField(MessageRestController.class, "userService", UserService.class);
                                                                                ReflectionUtils.makeAccessible(userServiceField);
                                                                                ReflectionUtils.setField(userServiceField, bean, attributes.get(0));
                                                                              });
                                                                                  return bean;
                                                                                }).register(beanFactory);
                                                                          }

                                                                          public static void registerProfileController(
                                                                              DefaultListableBeanFactory beanFactory) {
                                                                            BeanDefinitionRegistrar.of("profileController", ProfileController.class)
                                                                                .instanceSupplier((instanceContext) -> {
                                                                                  ProfileController bean = new ProfileController();
                                                                                  instanceContext.field("userService", UserService.class)
                                                                                      .invoke(beanFactory, (attributes) -> {
                                                                                        Field userServiceField = ReflectionUtils.findField(ProfileController.class, "userService", UserService.class);
                                                                                        ReflectionUtils.makeAccessible(userServiceField);
                                                                                        ReflectionUtils.setField(userServiceField, bean, attributes.get(0));
                                                                                      });
                                                                                          instanceContext.field("twotService", TwotService.class)
                                                                                              .invoke(beanFactory, (attributes) -> {
                                                                                                Field twotServiceField = ReflectionUtils.findField(ProfileController.class, "twotService", TwotService.class);
                                                                                                ReflectionUtils.makeAccessible(twotServiceField);
                                                                                                ReflectionUtils.setField(twotServiceField, bean, attributes.get(0));
                                                                                              });
                                                                                                  instanceContext.field("trtService", TwotRetwotService.class)
                                                                                                      .invoke(beanFactory, (attributes) -> {
                                                                                                        Field trtServiceField = ReflectionUtils.findField(ProfileController.class, "trtService", TwotRetwotService.class);
                                                                                                        ReflectionUtils.makeAccessible(trtServiceField);
                                                                                                        ReflectionUtils.setField(trtServiceField, bean, attributes.get(0));
                                                                                                      });
                                                                                                          instanceContext.field("followService", FollowService.class)
                                                                                                              .invoke(beanFactory, (attributes) -> {
                                                                                                                Field followServiceField = ReflectionUtils.findField(ProfileController.class, "followService", FollowService.class);
                                                                                                                ReflectionUtils.makeAccessible(followServiceField);
                                                                                                                ReflectionUtils.setField(followServiceField, bean, attributes.get(0));
                                                                                                              });
                                                                                                                  return bean;
                                                                                                                }).register(beanFactory);
                                                                                                          }

                                                                                                          public static void registerSearchController(
                                                                                                              DefaultListableBeanFactory beanFactory) {
                                                                                                            BeanDefinitionRegistrar.of("searchController", SearchController.class)
                                                                                                                .instanceSupplier((instanceContext) -> {
                                                                                                                  SearchController bean = new SearchController();
                                                                                                                  instanceContext.field("userService", UserService.class)
                                                                                                                      .invoke(beanFactory, (attributes) -> {
                                                                                                                        Field userServiceField = ReflectionUtils.findField(SearchController.class, "userService", UserService.class);
                                                                                                                        ReflectionUtils.makeAccessible(userServiceField);
                                                                                                                        ReflectionUtils.setField(userServiceField, bean, attributes.get(0));
                                                                                                                      });
                                                                                                                          return bean;
                                                                                                                        }).register(beanFactory);
                                                                                                                  }

                                                                                                                  public static void registerTwotController(
                                                                                                                      DefaultListableBeanFactory beanFactory) {
                                                                                                                    BeanDefinitionRegistrar.of("twotController", TwotController.class)
                                                                                                                        .instanceSupplier((instanceContext) -> {
                                                                                                                          TwotController bean = new TwotController();
                                                                                                                          instanceContext.field("twotService", TwotService.class)
                                                                                                                              .invoke(beanFactory, (attributes) -> {
                                                                                                                                Field twotServiceField = ReflectionUtils.findField(TwotController.class, "twotService", TwotService.class);
                                                                                                                                ReflectionUtils.makeAccessible(twotServiceField);
                                                                                                                                ReflectionUtils.setField(twotServiceField, bean, attributes.get(0));
                                                                                                                              });
                                                                                                                                  instanceContext.field("userService", UserService.class)
                                                                                                                                      .invoke(beanFactory, (attributes) -> {
                                                                                                                                        Field userServiceField = ReflectionUtils.findField(TwotController.class, "userService", UserService.class);
                                                                                                                                        ReflectionUtils.makeAccessible(userServiceField);
                                                                                                                                        ReflectionUtils.setField(userServiceField, bean, attributes.get(0));
                                                                                                                                      });
                                                                                                                                          instanceContext.field("retwotService", RetwotService.class)
                                                                                                                                              .invoke(beanFactory, (attributes) -> {
                                                                                                                                                Field retwotServiceField = ReflectionUtils.findField(TwotController.class, "retwotService", RetwotService.class);
                                                                                                                                                ReflectionUtils.makeAccessible(retwotServiceField);
                                                                                                                                                ReflectionUtils.setField(retwotServiceField, bean, attributes.get(0));
                                                                                                                                              });
                                                                                                                                                  return bean;
                                                                                                                                                }).register(beanFactory);
                                                                                                                                          }

                                                                                                                                          public static void registerTwotRestController(
                                                                                                                                              DefaultListableBeanFactory beanFactory) {
                                                                                                                                            BeanDefinitionRegistrar.of("twotRestController", TwotRestController.class)
                                                                                                                                                .instanceSupplier((instanceContext) -> {
                                                                                                                                                  TwotRestController bean = new TwotRestController();
                                                                                                                                                  instanceContext.field("twotService", TwotService.class)
                                                                                                                                                      .invoke(beanFactory, (attributes) -> {
                                                                                                                                                        Field twotServiceField = ReflectionUtils.findField(TwotRestController.class, "twotService", TwotService.class);
                                                                                                                                                        ReflectionUtils.makeAccessible(twotServiceField);
                                                                                                                                                        ReflectionUtils.setField(twotServiceField, bean, attributes.get(0));
                                                                                                                                                      });
                                                                                                                                                          instanceContext.field("userService", UserService.class)
                                                                                                                                                              .invoke(beanFactory, (attributes) -> {
                                                                                                                                                                Field userServiceField = ReflectionUtils.findField(TwotRestController.class, "userService", UserService.class);
                                                                                                                                                                ReflectionUtils.makeAccessible(userServiceField);
                                                                                                                                                                ReflectionUtils.setField(userServiceField, bean, attributes.get(0));
                                                                                                                                                              });
                                                                                                                                                                  instanceContext.field("retwotService", RetwotService.class)
                                                                                                                                                                      .invoke(beanFactory, (attributes) -> {
                                                                                                                                                                        Field retwotServiceField = ReflectionUtils.findField(TwotRestController.class, "retwotService", RetwotService.class);
                                                                                                                                                                        ReflectionUtils.makeAccessible(retwotServiceField);
                                                                                                                                                                        ReflectionUtils.setField(retwotServiceField, bean, attributes.get(0));
                                                                                                                                                                      });
                                                                                                                                                                          instanceContext.field("trtService", TwotRetwotService.class)
                                                                                                                                                                              .invoke(beanFactory, (attributes) -> {
                                                                                                                                                                                Field trtServiceField = ReflectionUtils.findField(TwotRestController.class, "trtService", TwotRetwotService.class);
                                                                                                                                                                                ReflectionUtils.makeAccessible(trtServiceField);
                                                                                                                                                                                ReflectionUtils.setField(trtServiceField, bean, attributes.get(0));
                                                                                                                                                                              });
                                                                                                                                                                                  instanceContext.field("followService", FollowService.class)
                                                                                                                                                                                      .invoke(beanFactory, (attributes) -> {
                                                                                                                                                                                        Field followServiceField = ReflectionUtils.findField(TwotRestController.class, "followService", FollowService.class);
                                                                                                                                                                                        ReflectionUtils.makeAccessible(followServiceField);
                                                                                                                                                                                        ReflectionUtils.setField(followServiceField, bean, attributes.get(0));
                                                                                                                                                                                      });
                                                                                                                                                                                          return bean;
                                                                                                                                                                                        }).register(beanFactory);
                                                                                                                                                                                  }
                                                                                                                                                                                }
