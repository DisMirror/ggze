package ggze.esp.dao.inter;
import org.apache.ibatis.annotations.*;

import java.util.List;
import ggze.esp.entity.LookRecord;
import org.springframework.stereotype.Component;

@Mapper
public interface LookRecordDao{
    @Insert("insert into LookRecord (${name0}) values ('${value0}')")
    int add(@Param("name0")String n,@Param("value0")String v);

    @Delete("delete from LookRecord where ${name1}='${value1}'")
    int delete(@Param("name1")String n,@Param("value1")String v);

    @Update("update LookRecord set ${name2}='${value2}' where ${name4}='${value4}'")
    int update(@Param("name2")String n,@Param("value2")String v,@Param("name4")String va,@Param("value4")String vas);

    @Select("select * from LookRecord")
    List<LookRecord> allSelect();

    @Select("select  * from LookRecord where ${name3}='${value3}'")
    LookRecord isSelect(@Param("name3")String n,@Param("value3")String v);

    @Select("select  * from LookRecord where ${tjname}='${tjva}' order by uuid limit ${begin},${end}")
    List<LookRecord> paging(@Param("tjname")String tjname,@Param("tjva")String tjva,@Param("begin")int begin,@Param("end")int end);

    @Select("select  * from LookRecord where ${ming}='${zhi}'")
    List<LookRecord> isSelectSome(@Param("ming")String ming,@Param("zhi")String zhi);

    @Select("${sql}")
    LookRecord custom(@Param("sql")String sql);

    @Select("select count(*) from LookRecord where ${sqlName}='${sqlVal}'")
    int getNum(@Param("sqlName")String sqlName,@Param("sqlVal")String sqlVal);
}