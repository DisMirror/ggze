/*************创建接口***************/
package ggze.esp.dao.inter;
import org.apache.ibatis.annotations.*;

import java.util.List;
import ggze.esp.entity.Api;

public interface ApiDao{
    @Insert("insert into Api (${name0}) values ('${value0}')")
    int add(@Param("name0")String n,@Param("value0")String v);

    @Delete("delete from Api where ${name1}='${value1}'")
    int delete(@Param("name1")String n,@Param("value1")String v);

    @Update("update Api set ${name2}='${value2}' where ${name4}='${value4}'")
    int update(@Param("name2")String n,@Param("value2")String v,@Param("name4")String va,@Param("value4")String vas);

    @Select("select * from Api")
    List<Api> allSelect();

    @Select("select  * from Api where ${name3}='${value3}'")
    Api isSelect(@Param("name3")String n,@Param("value3")String v);

    @Select("select  * from Api where ${tjname}='${tjva}' order by uuid limit ${begin},${end}")
    List<Api> paging(@Param("tjname")String tjname,@Param("tjva")String tjva,@Param("begin")int begin,@Param("end")int end);

    @Select("select  * from Api where ${ming}='${zhi}'")
    List<Api> isSelectSome(@Param("ming")String ming,@Param("zhi")String zhi);

    @Select("${sql}")
    Api custom(@Param("sql")String sql);

    @Select("select count(*) from Api where ${sqlName}='${sqlVal}'")
    int getNum(@Param("sqlName")String sqlName,@Param("sqlVal")String sqlVal);
}
