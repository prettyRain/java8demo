package com.ge.stream;

import com.ge.lambda.Student;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 中间操作
 *  筛选与切片
 *    filter——接收 Lambda ， 从流中排除某些元素。
 *    limit——截断流，使其元素不超过给定数量。
 *    skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
 *    distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
 *    sorted()——自然排序
 *    sorted(Comparator com)——定制排序
 *
 *  映射
 *    map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
 *    flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
 *
 */
public class TestStream2 {
    Student[]  aa = new Student[]{new Student(1L,"小明","男",18,new BigDecimal(5000)),
            new Student(2L,"小花","女",12,new BigDecimal(6000)),
            new Student(3L,"小李","男",10,new BigDecimal(3000)),
            new Student(4L,"小刘","男",17,new BigDecimal(9000)),
            new Student(5L,"小刚","男",13,new BigDecimal(8000))};
    /**
     *    filter——接收 Lambda ， 从流中排除某些元素。
     *    limit——截断流，使其元素不超过给定数量。
     *    skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
     *    distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
     */
    //内部迭代
    @Test
    public void test1(){
        //所有的中间操作不会做任何的处理
        Stream<Student> stream = Arrays.stream(aa)
                .filter((e) -> e.getAge() > 12l)
                .limit(3)
                .distinct()
                .skip(1);

        //只有当终止操作时，所有的操作才会一次性执行，称为“惰性求值”
        stream.forEach(System.out::println);
    }


    /**
     *    map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     *    flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
     @Test
     public void test2(){

         Stream<Student> studentStream = Arrays.stream(aa);
         studentStream.map( (t) -> t.getName())
                 .forEach(System.out::println);

         List<String> strings = Arrays.asList("aa", "bb", "cc", "dd");
         Stream<String> stream = strings.stream()
                       .map(String::toUpperCase);
          stream.forEach(System.out::println);

         //map流中有流
         Stream<Stream<String>> stream1 = strings.stream()
                 .map(TestStream2::getstream);
         stream1.forEach(xs -> xs.forEach(System.out::println));


         //flatMap 把所有流连成一个流
         Stream<String> stream2 = strings.stream()
                 .flatMap(TestStream2::getstream);
         stream2.forEach(System.out::println);

     }


     public static Stream<String> getstream(String str){
         List<String> list = new ArrayList<>();
         char[] chars = str.toCharArray();
         for(int i = 0 ; i < chars.length ; i++){
             list.add(String.valueOf(chars[i]));
         }
         return list.stream();
     }


    /**
     * sorted()——自然排序
     * sorted(Comparator com)——定制排序
     */
    @Test
     public void test3(){
         Stream<Student> stream = Arrays.stream(aa);
         stream.map(x -> x.getName())
               .sorted()
               .forEach(System.out::println);


        Arrays.stream(aa).sorted((x,y) -> {
                   if(x.getAge()==y.getAge()){
                       return x.getName().compareTo(y.getName());
                   }else{
                       return Integer.compare(x.getAge(),y.getAge());
                   }
                })
              .forEach(System.out::println);
    }

}
