package com.ge.stream;

import com.ge.lambda.Student;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*
 * 一、Stream API 的操作步骤：
 *
 * 1. 创建 Stream
 */
public class TestStream1 {

    /**
     * 创建 stream
     */
    @Test
    public void test1(){
        //1.Collection提供了两个方法  stream() 和 parallelStream()
        List<String> list = new ArrayList<String>();
        Stream<String> stream = list.stream();
        Stream<String> stringStream = list.parallelStream();

        //2.通过Arrays中的stream()获取一个数组流
        Integer[] nums = new Integer[10];
        Stream<Integer> stream1 = Arrays.stream(nums);

        //3.通过stream类中的静态方法 of()
        Stream<Integer> stream2 = Stream.of(1,2,3,4,5);

        //4.创建无限流
        //迭代
        Stream<Integer> stream3 = Stream.iterate(2,(x) -> x+2).limit(5);
        stream3.forEach(System.out::println);
        //生成   里面是供给型
        Stream<Double> stream4 = Stream.generate(Math::random).limit(2);
        //里面是消费型
        stream4.forEach(System.out::println);
    }


}
