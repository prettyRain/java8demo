package com.ge.lambda;

/**
 * 判断年龄的实现类
 * Created by Administrator on 2019/3/15.
 */
public class FilterStudentForAgeImpl implements FilterStudent<Student> {

    @Override
    public boolean test(Student student) {
        if(student.getAge() > 12){
            return true;
        }else{
            return false;
        }
    }
}
