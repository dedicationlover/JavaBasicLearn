package com.annotation.demo1;//package com.annotation;
//
//
//import java.io.FileOutputStream;
//import java.io.PrintStream;
//
///**
// * 为自定义的三个注解提供Annotation处理器，该处理器的功能是根据注解来生成一个
// * Hibernate映射文件（可以根据这些Annotation来生成另一份XML文件）
// */
//public class HibernateAnnotationProcessor implements AnnotationProcessor
//{
//    // Annotation处理器环境，是该处理器与APT交互的重要途径
//    private AnnotationProcessorEnvironment env;
//
//    // 构造HibernateAnnotationProcessor对象时，获得处理器环境
//    public HibernateAnnotationProcessor(AnnotationProcessorEnvironmet env)
//    {
//        this.env = env;
//    }
//
//    // 循环处理每个对象
//    public void process()
//    {
//        // 遍历每个class文件
//        for (TypeDeclaration t : env.getSpecifiedTtpeDeclarations())
//        {
//            // 定义一个文件输出流，用于生成额外的文件
//            FileOutputStream fos = null;
//            // 获取正在处理的类名
//            String clazzName = t.getSimpleName();
//            // 获取类定义前的Persistent Annotation
//            Persistent per = t.getAnnotation(Persistent.class);
//            // 当per Annotation不为空时才继续处理
//            if(per != null)
//            {
//                try
//                {
//                    // 创建文件输出流
//                    fos  = new FileOutputStream(clazzName + ".hbm.xml");
//                    PrintStream ps = new PrintStream(fos);
//                    // 执行输出
//                    ps.println("<?xml version = \"1.0\"?>");
//                    ps.println("<!DOCTYPE hibernate-mapping");
//                    ps.println("  PUBLIC \"-//hibernate/Hibernate Mapping DTD 3.0//EN\"");
//                    ps.println("<hibernate-mapping>");
//                    ps.print(" <class name = \"" + t);
//                    // 输出per的table()的值
//                    ps.println("\"table\"" + per.table() + "\">");
//                    for(FieldDeclaration f : t.getFields())
//                    {
//                        // 获取指定FieldDeclaration前面的IdProperty Annotation
//                        IdProperty id = f.getAnnotation(IdProperty.class);
//                        // 如果id Annotation不为空
//                        if(id != null)
//                        {
//                            // 执行输出
//                            ps.println("    <id name = \"" + f.getSimpleName() +
//                                    "\"column=\"" + id.column() +
//                                    "\"type = \"" + id.type() + "\">");
//                            ps.println("    <generator class = \"" + id.generator() + "\"/>");
//                            ps.println("    </id>");
//                        } // end if
//                        // 获取指定FieldDeclaration前面的Property Annotation
//                        Property p = f.getAnnotation(Property.class);
//                        // 如果p Annotation不为空
//                        if(p != null)
//                        {
//                            // 执行输出
//                            ps.println("   <property name = \""
//                                    + f.getSimpleName()
//                                    + "\" column = \"" + p.column()
//                                    + "\" type = \"" + p.type()
//                                    + "\"/>");
//                        } // end if
//                    } // end for
//                    ps.println("    </class>");
//                    ps.println("</hibernate-mapping>");
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//                finally
//                {
//                    try
//                    {
//                        // 关闭输出流
//                        if(fos != null)
//                        {
//                            fos.close();
//                        }
//                    }
//                    catch (IOException e)
//                    {
//                        e.printStackTrace();
//                    } // end try
//                } // end try
//            } // end if
//
//        }
//    }
//}
