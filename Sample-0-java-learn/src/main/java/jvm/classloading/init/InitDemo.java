package jvm.classloading.init;

/**
 * 初始化阶段：执行类加载器的<client>()方法，赋值和静态语句块
 *
 * 子类的<client>()方法执行之前父类的<client>()
 *
 * 意味着：
 * 1. 父类的静态语句块优先于子类的变量赋值
 *
 * 2. <client>()对于类或接口不必须，如果没静态语句块或赋值语句则不为这个类生成<client>()
 *
 * 3. 只有使用父接口定义的变量时，父接口会初始化；但是类的话，父类一定会初始化
 *
 * 4. <client>()方法在多线程环境中会被加锁
 *
 */
public class InitDemo {

   static class Parent {
       public static int A = 1;
       static {
           A = 2;
       }
   }

   static class Sub extends Parent {
       public static int B = A;
   }

    public static void main(String[] args) {
        System.out.println(Sub.B);
    }

}
