package com.ge.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/*
 * 一、Lambda 表达式的基础语法：Java8中引入了一个新的操作符 "->" 该操作符称为箭头操作符或 Lambda 操作符
 * 箭头操作符将 Lambda 表达式拆分成两部分：
 *     左侧：Lambda 表达式的参数列表
 *     右侧：Lambda 表达式中所需执行的功能， 即 Lambda 体
 *
 * 语法格式一：无参数，无返回值
 * 		() -> System.out.println("Hello Lambda!");
 *
 * 语法格式二：有一个参数，并且无返回值
 * 		(x) -> System.out.println(x)
 *
 * 语法格式三：若只有一个参数，小括号可以省略不写
 * 		x -> System.out.println(x)
 *
 * 语法格式四：有两个以上的参数，有返回值，并且 Lambda 体中有多条语句
 *		Comparator<Integer> com = (x, y) -> {
 *			System.out.println("函数式接口");
 *			return Integer.compare(x, y);
 *		};
 *
 * 语法格式五：若 Lambda 体中只有一条语句，有返回值， return 和 大括号都可以省略不写
 * 		Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
 *
 * 语法格式六：Lambda 表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，数据类型，即“类型推断”
 * 		(Integer x, Integer y) -> Integer.compare(x, y);
 *
 *
 * 二、Lambda 表达式需要“函数式接口”的支持
 * 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。 可以使用注解 @FunctionalInterface 修饰
 * 			 可以检查是否是函数式接口
 */
public class TestLambda2 {

    /**
     * 无参 无返回值
     */
    @Test
    public void test1(){
        Runnable r = () -> System.out.println("lambda");
        r.run();
    }

    /**
     * 有一个参数，并且无返回值
     */
    @Test
    public void test2(){
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("有一个参数，并且无返回值");
    }

    /**
     * 若只有一个参数，小括号可以省略不写
     */
    @Test
    public void test3(){
        Consumer<String> con = x -> System.out.println(x);
        con.accept("若只有一个参数，小括号可以省略不写");
    }

    /**
     * 有两个以上的参数，有返回值，并且 Lambda 体中有多条语句
     */
    @Test
    public void test4(){
        Comparator<Integer> comparator = (x,y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x,y);
        };
        System.out.println(comparator.compare(5,6));
    }
    /**
     * 若 Lambda 体中只有一条语句，有返回值， return 和 大括号都可以省略不写
     */
    @Test
    public void test5(){
        Comparator<Integer> comparator = (x,y) -> Integer.compare(x,y);
        System.out.println(comparator.compare(5,6));
    }

    /**
     * Lambda 表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，数据类型，即“类型推断”
     */
    @Test
    public void test6(){
        Comparator<Integer> comparator = (Integer x,Integer y) -> Integer.compare(x,y);
        System.out.println(comparator.compare(5,6));
    }

}
