package com.ge.lambda;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/*
 * 一、方法引用：若 Lambda 体中的功能，已经有方法提供了实现，可以使用方法引用
 * 			  （可以将方法引用理解为 Lambda 表达式的另外一种表现形式）
 *
 * 1. 对象的引用 :: 实例方法名
 *
 * 2. 类名 :: 静态方法名
 *
 * 3. 类名 :: 实例方法名
 *
 * 注意：
 * 	 ①方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致！
 * 	 ②若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName
 *
 * 二、构造器引用 :构造器的参数列表，需要与函数式接口中参数列表保持一致！
 *
 * 1. 类名 :: new
 *
 * 三、数组引用
 *
 * 1. 类型[] :: new;
 *
 *
 */
public class TestLambda4 {

    /**
     * 对象的引用 :: 实例方法名
     */
    @Test
    public void test1(){
        //消费型
        Consumer<String> consumer = (x) -> System.out.println(x);
        Consumer<String> consumer1 = System.out::println;
        consumer.accept("consumer:以前的写法");
        consumer.accept("consumer1:现在的写法");
        //供给型
        Student student = new Student(1l,"小明","男",12,new BigDecimal(2000));
        Supplier<String> supplier = student::getName;
        System.out.println(supplier.get());
    }


    /**
     * 类名 :: 静态方法名
     */
    @Test
    public void test2(){
        //函数型接口
        Function<Integer,String> fun = Student::getStr;
        System.out.println(fun.apply(100));
    }

    /**
     * 类名 :: 实例方法名   把类名当一个参数来处理
     */
    @Test
    public void test3(){
        //断言型接口
        BiPredicate<String,String>  biPredicate = (x,y) -> x.equals(y);
        System.out.println(biPredicate.test("222","333"));

        BiPredicate<String,String> biPredicate1 = String::equals;
        System.out.println(biPredicate1.test("222","333"));

        Function<Student,String> fun = (e) -> e.toString();
        System.out.println(fun.apply(new Student(1l,"小明","男",12,new BigDecimal(2000))));

        Function<Student,String> fun1 = Student::toString;
        System.out.println(fun.apply(new Student(1l,"小明","男",12,new BigDecimal(2000))));
    }

    /**
     * 构造器引用
     */
    @Test
    public void test4(){
       Supplier<Student> supplier = () -> new Student(1l,"小明","男",12,new BigDecimal(2000));
       System.out.println(supplier.get().toString());

       Supplier<Student> studentSupplier = Student::new;
       System.out.println(studentSupplier.get().toString());
    }

    /**
     * 数组引用
     */
    @Test
    public void test5(){
       //函数型接口
        Function<Integer,String[]> function = (x) -> new String[x];
        System.out.println(function.apply(3).length);

        Function<Integer,String[]> function1 = String[]::new;
        System.out.println(function1.apply(3).length);

    }
}
