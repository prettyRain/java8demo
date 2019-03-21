package com.ge.lambda;

import org.junit.Test;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/*
 * Java8 内置的四大核心函数式接口
 *
 * Consumer<T> : 消费型接口
 * 		void accept(T t);
 *
 * Supplier<T> : 供给型接口
 * 		T get();
 *
 * Function<T, R> : 函数型接口
 * 		R apply(T t);
 *
 * Predicate<T> : 断言型接口
 * 		boolean test(T t);
 *
 */
public class TestLambda3 {

    /**
     * Consumer<T>消费型接口
     */
    @Test
    public void test1(){
        Consumer<String> consumer = x -> System.out.println(x);
        consumer.accept("consumer消费型接口");
    }
    /**
     * Supplier<T>供给型接口
     */
    @Test
    public void test2(){
        Supplier<Integer> supplier= () -> (int)(Math.random()*100);
        System.out.println(supplier.get());
    }
    /**
     * Function(T,R)函数型接口
     */
    @Test
    public void test3(){
        String resultstr = strHander("jjfsdjsjdsjiojwijf",(x) -> x.substring(2,5));
        System.out.println(resultstr);
    }
    public String strHander(String str, Function<String,String> fun){
        return  fun.apply(str);
    }

    /**
     * 断言型接口
     */
    @Test
    public void test4(){
        List<String> list = Arrays.asList("aaa","bbb","cccc","dddd");
        List<String> returnlist = getList(list,(x) -> x.length() > 3);
        System.out.println(Arrays.toString(returnlist.toArray()));
    }
    public List getList(List<String> list, Predicate<String> predicate){
        ArrayList<String> returnlist = new ArrayList();
        for(String str:list){
            if(predicate.test(str)){
                returnlist.add(str);
            }
        }
        return returnlist;
    }

}
