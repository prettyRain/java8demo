package com.ge.stream;

import com.ge.lambda.Student;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 终止操作
 *   allMatch——检查是否匹配所有元素
 *   anyMatch——检查是否至少匹配一个元素
 *   noneMatch——检查是否没有匹配的元素   没有匹配返回true
 *   findFirst——返回第一个元素
 *   findAny——返回当前流中的任意元素
 *   count——返回流中元素的总个数
 *   max——返回流中最大值
 *   min——返回流中最小值
 *
 *   归约
 *   reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。
 *
 *   收集
 *   collect——将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
 *   注意：流进行了终止操作后，不能再次使用
 */
public class TestStream3 {

    public List<Student> bb = Arrays.asList(new Student(1L,"小明","男",18,new BigDecimal(5000)),
            new Student(2L,"小花","女",17,new BigDecimal(6000)),
            new Student(3L,"小李","男",10,new BigDecimal(3000)),
            new Student(4L,"小刘","男",17,new BigDecimal(9000)),
            new Student(5L,"小刚","男",13,new BigDecimal(8000)));

    /**
     * allMatch/anyMatch/noneMatch/findFirst/findAny/count/max/min
     */
    @Test
    public void test1(){
        boolean b = bb.stream()
                .allMatch(x -> x.getAge() == 12);
        System.out.println(b);

        boolean b1 = bb.stream()
                .anyMatch(x -> x.getAge() == 12);
        System.out.println(b1);

        boolean b2 = bb.stream()
                .noneMatch(x -> x.getAge() == 19);
        System.out.println(b2);

        Optional<Student> first = bb.stream()
                .sorted((x, y) -> x.getAge().compareTo(y.getAge()))
                .findFirst();
        System.out.println(first.get());

        Optional<Student> any = bb.stream()
                .filter(x -> x.getPrice().compareTo(BigDecimal.valueOf(5000)) > 0)
                .findAny();
        System.out.println(any.get());

        long count = bb.stream()
                .filter(x -> x.getPrice().compareTo(BigDecimal.valueOf(5000)) > 0)
                .count();
        System.out.println(count);

        Optional<Integer> max = bb.stream()
                .map(Student::getAge)
                .max((x, y) -> x.compareTo(y));
        System.out.println(max);

        Optional<Student> min = bb.stream()
                .min((x, y) -> x.getAge().compareTo(y.getAge()));
        System.out.println(min.get());
    }

    /**
     * reduce 归约
     */
    @Test
    public void test2(){
        //从零开始加
        Integer ages = bb.stream()
                .map(Student::getAge)
                .reduce(0, (x, y) -> x + y);
        System.out.println(ages);

        //没有初始的数
        Optional<Integer> reduce = bb.stream()
                .map(Student::getAge)
                .reduce((x, y) -> x + y);
        System.out.println(reduce.get());
    }

    /**
     * collect
     * 返回集合
     */
    @Test
    public void test3(){
        List<Integer> collect = bb.stream()
                .map(Student::getAge)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);

        Map<String, BigDecimal> collect1 = bb.stream()
                .collect(Collectors.toMap(x -> x.getName(), x -> x.getPrice()));
        Set<Map.Entry<String,BigDecimal>> entrySet = collect1.entrySet();
        for(Map.Entry<String,BigDecimal> entry:entrySet){
            System.out.println(entry.getKey()+"=="+entry.getValue());
        }

        HashSet<Student> collect2 = bb.stream()
                .collect(Collectors.toCollection(HashSet::new));
        collect2.forEach(System.out::println);
    }
    /**
     * collect
     * 最大值  最小值  总和  平均值  总个数
     */
    @Test
    public void test4(){
        Optional<BigDecimal> collect = bb.stream()
                .map(Student::getPrice)
                .collect(Collectors.minBy((x, y) -> x.compareTo(y)));
        System.out.println(collect.get());

        Optional<BigDecimal> collect1 = bb.stream()
                .map(Student::getPrice)
                .collect(Collectors.maxBy((x, y) -> x.compareTo(y)));
        System.out.println(collect1.get());

        Double collect3 = bb.stream()
                .map(Student::getPrice)
                .collect(Collectors.summingDouble(x -> Double.valueOf(x.toString())));
        System.out.println(collect3);

        Double collect4 = bb.stream()
                .map(Student::getPrice)
                .collect(Collectors.averagingDouble(x -> Double.valueOf(x.toString())));
        System.out.println(collect4);

        Long collect5 = bb.stream()
                .collect(Collectors.counting());
        System.out.println(collect5);

        DoubleSummaryStatistics collect2 = bb.stream()
                .map(Student::getPrice)
                .collect(Collectors.summarizingDouble(x -> Double.valueOf(x.toString())));
        System.out.println(collect2.getAverage());
        System.out.println(collect2.getCount());
        System.out.println(collect2.getSum());
        System.out.println(collect2.getMax());
        System.out.println(collect2.getMin());

    }

    /**
     * collect
     * 分组
     */
    @Test
    public void test5(){
        //分组
        Map<Integer, List<Student>> collect = bb.stream()
                .collect(Collectors.groupingBy((e) -> e.getAge()));
        for(Map.Entry<Integer,List<Student>> entry: collect.entrySet()){
            System.out.println("分组的key"+entry.getKey());
            entry.getValue().forEach(System.out::println);
        }

        //多级分组
        Map<Integer, Map<BigDecimal, List<Student>>> collect1 = bb.stream()
                .collect(Collectors.groupingBy(e -> e.getAge(), Collectors.groupingBy(e -> e.getPrice())));
        System.out.println(collect1);
    }

    /**
     * collect
     * 分区
     */
    @Test
    public void test6(){
        Map<Boolean, List<Student>> collect = bb.stream()
                .collect(Collectors.partitioningBy((e) -> e.getAge() > 12));
        for(Map.Entry<Boolean,List<Student>> entry : collect.entrySet()){
            System.out.println("分组的key"+entry.getKey());
            entry.getValue().forEach(System.out::println);
        }
    }

    /**
     * collect
     * join
     */
    @Test
    public void test7(){
        String collect = bb.stream()
                .map(Student::getName)
                .collect(Collectors.joining(",", "开始--", "--结束"));
        System.out.println(collect);
    }
}
