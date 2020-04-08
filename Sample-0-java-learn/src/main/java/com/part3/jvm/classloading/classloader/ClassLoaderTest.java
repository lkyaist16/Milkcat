package com.part3.jvm.classloading.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * 示例：
 *    即使这两个类都来源于同一个Class文件，但是属于两个不同的类加载器
 *    一个是由系统应用程序类加载器加载的，另一个由自定义的类加载器
 *
 * 结论：
 *    即使两个类来源于同一个Class文件，被同一个虚拟机加载，只要加载它们的类加载器不同，那么这两个类必定不相等
 *
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws Exception {

        //自定义一个类加载器
        ClassLoader myLoader = new ClassLoader() {
            /**
             * Loads the class with the specified <a href="#name">binary name</a>.
             * This method searches for classes in the same manner as the {@link
             * #loadClass(String, boolean)} method.  It is invoked by the Java virtual
             * machine to resolve class references.  Invoking this method is equivalent
             * to invoking {@link #loadClass(String, boolean) <tt>loadClass(name,
             * false)</tt>}.
             *
             * @param name The <a href="#name">binary name</a> of the class
             * @return The resulting <tt>Class</tt> object
             * @throws ClassNotFoundException If the class was not found
             */
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {

                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";

                    InputStream is = getClass().getResourceAsStream(fileName);

                    if (is == null) {
                        return super.loadClass(name);
                    }

                    byte[] b = new byte[0];

                    b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        //用自定义的类加载器去加载一个类
        Object obj = myLoader.loadClass("com.part3.jvm.classloading.classloader.ClassLoaderTest").newInstance();

        System.out.println(obj.getClass());

        //与相对路径的类相对比
        System.out.println(obj instanceof ClassLoaderTest);

    }

}
