package com.ge.lambda;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Administrator on 2019/3/15.
 */
public class TestLambda1 {


    Student[]  aa = new Student[]{new Student(1L,"小明","男",18,new BigDecimal(5000)),
                                  new Student(2L,"小花","女",12,new BigDecimal(6000)),
                                  new Student(3L,"小李","男",10,new BigDecimal(3000)),
                                  new Student(4L,"小刘","男",17,new BigDecimal(9000)),
                                  new Student(5L,"小刚","男",13,new BigDecimal(8000))};
    List<Student> stus = Arrays.asList(aa);
    //以前的内部匿名类
    @Test
    public void test1(){
        Comparator comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(),o2.length());
            }
        };
        TreeSet ts = new TreeSet(comparator);
    }
    //现在lambda表达式
    @Test
    public void test2(){
        Comparator<String> comparator = (x,y) -> Integer.compare(x.length(),y.length());
        TreeSet ts = new TreeSet(comparator);
    }

    @Test
    public void test6(){
        //以前的匿名内部类
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("lambda");
            }
        };
        runnable.run();

        //现在的lambda表达式
        Runnable runnable1 = () -> System.out.println("lambda");
        runnable1.run();
    }

    //策略设计模式
    public List<Student> filterStudent(List<Student> students,FilterStudent<Student> filterStudent){
        List<Student> studentList = new ArrayList<Student>();
        for(Student student:students){
            if(filterStudent.test(student)){
                studentList.add(student);
            }
        }
        return studentList;
    }

    /**
     * 普通调用实现类
     */
    @Test
    public void test3(){
       List<Student> listForAge = filterStudent(stus,new FilterStudentForAgeImpl());
       for(Student student:listForAge){
           System.out.println(student.toString());
       }

       System.out.println("-----------------------------");

       List<Student> listForPrice = filterStudent(stus,new FilterStudentForPriceImpl());
        for(Student student:listForPrice){
            System.out.println(student.toString());
        }
    }

    /**
     * 通过匿名类实现
     */
    @Test
    public void test4(){
        List<Student> list = filterStudent(stus,new FilterStudent<Student>(){
            @Override
            public boolean test(Student student) {
                if(student.getAge() > 13){
                    return true;
                }
                return false;
            }
        });
        for(Student student:list){
            System.out.println(student.toString());
        }
    }

    /**
     * lambda表达式
     */
    @Test
    public void test5(){
        List<Student> list = filterStudent(stus,(e) -> e.getAge() >=  12 );
        for(Student student:list){
            System.out.println(student.toString());
        }

        System.out.println("-------------------------");

        List<Student> list1 = filterStudent(stus,(e) -> e.getPrice().compareTo(new BigDecimal(8000)) >= 0 );
        for(Student student:list1){
            System.out.println(student.toString());
        }
    }


}
