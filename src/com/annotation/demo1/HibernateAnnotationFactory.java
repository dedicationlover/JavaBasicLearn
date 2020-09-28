package com.annotation.demo1;//package com.annotation;
//
///**
// * 为该Annotation处理器提供一个处理工厂，处理工厂负责决定该处理器支持哪些Annotation，
// * 并通过getProcessorFor方法来生成一个Annotation处理器对象
// */
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.Set;
//
//public class HibernateAnnotationFactory implements AnnotationProcessorFactory {
//
//    // 所有支持的注释类型
//    public Collection<String> supportedAnnotationTypes(){
//        return Arrays.asList("Property", "IdProperty", "Persistent");
//    }
//
//    // 返回所有支持的选项
//    public Collection<String> supportedOptions(){
//        return Arrays.asList(new String[0]);
//    }
//
//    // 返回Annotation处理器
//    public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> atds, AnnotationProcessorEnvironment env){
//        return new HibernateAnnotationProcessor(env);
//    }
//} // end HibernateAnnotationFactory
//
