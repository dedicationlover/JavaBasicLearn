package com.reflection;

/**
 * 测试各种类型（class, interface, annotation, enum, primitive type, void)
 * java.lang.Class对象的获取方式
 */
@SuppressWarnings("all")
public class demo
{
    public static void main(String[] args)
    {
        String path = "demo";

        try
        {
            // 对象是表示或封装一些数据。一个类被加载后，JVM会创建一个对应该类的Class对象，
            // 类的整个结构信息会放到对应的Class对象中
            // 这个Class结构就像一面镜子，通过这面镜子可以看到对应类的全部信息---反射
            Class clazz = Class.forName(path);
            System.out.println(clazz);  // class demo
            System.out.println(clazz.hashCode());  // 685325104

            Class clazz2 = Class.forName(path);
            System.out.println(clazz2.hashCode());  // 685325104
            // 一个类只会被加载一次，只有一个Class对象（反射对象）（一张图纸）

            Class clazz3 = String.class;
            Class clazz4 = path.getClass();
            System.out.println(clazz3 == clazz4);  // true

            Class intClazz = int.class;
            System.out.println(intClazz);  // int

            int[] array = new int[5];
            int[] array2 = new int[4];
            int[][] arr = new int[4][8];
            Class arrayClazz = array.getClass();
            Class arrayClazz2 = array2.getClass();
            Class arrClazz = arr.getClass();
            System.out.println(arrayClazz);     // class [I
            System.out.println(arrayClazz2);    // class [I
            System.out.println(arrClazz);       // class [[I
            System.out.println(arrayClazz.hashCode());   // 460141958
            System.out.println(arrayClazz2.hashCode());  // 460141958
            System.out.println(arrClazz.hashCode());     // 1163157884
            System.out.println(arrayClazz2 == arrClazz); // false

            double[] d = new double[45];
            double[][] dd = new double[4][2];
            System.out.println(d.getClass());   // class [D
            System.out.println(dd.getClass());  // class [[D
            System.out.println(d.getClass().hashCode());  // 1956725890
            System.out.println(dd.getClass().hashCode()); // 356573597

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
