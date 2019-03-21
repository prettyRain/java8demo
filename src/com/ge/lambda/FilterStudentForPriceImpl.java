package com.ge.lambda;

import java.math.BigDecimal;

/**
 * 判断学生工资的实现类
 * Created by Administrator on 2019/3/15.
 */
public class FilterStudentForPriceImpl implements FilterStudent<Student> {
    @Override
    public boolean test(Student student) {
        if(student.getPrice().compareTo(new BigDecimal(5000)) >= 0){
            return true;
        }
        return false;
    }
}
