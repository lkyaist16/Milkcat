package java8.classloading.classloader;

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

        Object obj = myLoader.loadClass("java8.classloading.classloader.ClassLoaderTest").newInstance();

        System.out.println(obj.getClass());

        System.out.println(obj instanceof java8.classloading.classloader.ClassLoaderTest);

    }

}
