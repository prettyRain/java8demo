package com.ge.lambda;

import java.math.BigDecimal;

/**
 * 学生类
 * Created by Administrator on 2019/3/15.
 */
public class Student {

    public static String getStr(Integer grade){

        if(Integer.compare(grade,60) > 0){
            return "三好学生";
        }
        return "不及格";
    }
    private Long id;//id
    private String name;//学生姓名
    private String sex;//性别
    private Integer age;//年龄
    private BigDecimal price;//工资

    public Student() {
    }

    public Student(Long id, String name, String sex, Integer age, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (!id.equals(student.id)) return false;
        if (!name.equals(student.name)) return false;
        if (!sex.equals(student.sex)) return false;
        if (!age.equals(student.age)) return false;
        return price.equals(student.price);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + sex.hashCode();
        result = 31 * result + age.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", price=" + price +
                '}';
    }
}
