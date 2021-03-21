package com.cjw.eshare.dao;

import com.cjw.eshare.entity.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author cj.w
 * @date 2021/1/4 23:26
 */
@Mapper
public interface TypeDao {

    @Insert("insert into types(name,create_at,update_at) value(#{name}, #{create_at}, #{update_at})")
    void createType(Type type);

    @Delete("delete from types where id = #{id}")
    void deleteType(Integer id);

    @Select("select * from types")
    List<Type> findAllType();

    @Select("select * from types where id = #{id}")
    Type findById(Integer id);

    @Update("update types set name = #{name}, update_at = #{update_at} where id = #{id}")
    void updateTypeById(Type type);

    @Select("select * from types where name = #{typeName}")
    Type findByName(@Param("typeName") String typeName);
}
