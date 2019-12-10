package io.github.mylyed.ds.dao;

import org.apache.ibatis.annotations.Select;

public interface TestMapper {

    @Select("SELECT USER()")
    String findUser();


    @Select("SELECT USER()")
    String masterFindUser();

}