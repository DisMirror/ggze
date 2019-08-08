package ggze.esp.dao.inter;
import org.apache.ibatis.annotations.*;

import java.util.List;
import ggze.esp.entity.DataRecord;
import org.springframework.stereotype.Component;

@Mapper
public interface DataRecordDao{
    @Insert("insert into DataRecord (${name0}) values ('${value0}')")
    int add(@Param("name0")String n,@Param("value0")String v);

    @Delete("delete from DataRecord where ${name1}='${value1}'")
    int delete(@Param("name1")String n,@Param("value1")String v);

    @Update("update DataRecord set ${name2}='${value2}' where ${name4}='${value4}'")
    int update(@Param("name2")String n,@Param("value2")String v,@Param("name4")String va,@Param("value4")String vas);

    @Select("select * from DataRecord")
    List<DataRecord> allSelect();

    @Select("select  * from DataRecord where ${name3}='${value3}'")
    DataRecord isSelect(@Param("name3")String n,@Param("value3")String v);

    @Select("select  * from DataRecord where ${tjname}='${tjva}' order by uuid limit ${begin},${end}")
    List<DataRecord> paging(@Param("tjname")String tjname,@Param("tjva")String tjva,@Param("begin")int begin,@Param("end")int end);

    @Select("select  * from DataRecord where ${ming}='${zhi}'")
    List<DataRecord> isSelectSome(@Param("ming")String ming,@Param("zhi")String zhi);

    @Select("${sql}")
    DataRecord custom(@Param("sql")String sql);

    @Select("select count(*) from DataRecord where ${sqlName}='${sqlVal}'")
    int getNum(@Param("sqlName")String sqlName,@Param("sqlVal")String sqlVal);
}