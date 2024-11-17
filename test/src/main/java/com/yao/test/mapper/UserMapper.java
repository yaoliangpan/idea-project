package com.yao.test.mapper;


import com.yao.test.controller.PageQueryVo;
import com.yao.test.controller.vo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    /**
     * 分页查询用户信息
     * @param page
     * @return
     */
    vo[] selectUserPage(PageQueryVo page);

    /**
     * 根据条件查询用户总数
     * @param page
     * @return
     */
    Long selectUserCount(PageQueryVo page);

    @Insert("insert into user(userName, age,createDate ) values(#{userName}, #{age}, #{createDate})")
    void addUser(vo user);

    /**
     * 根据用户名查询用户(是否重复)
     * @param userName
     * @return
     */
    @Select("select count(*) from user where userName = #{userName}")
    Long selectUserByName(String userName);

    /**
     * 登录(查询用户)
     * @param user
     * @return
     */
    @Select("select count(*) from user where userName = #{user} and password = #{password}")
    Long login(String user,Long password);

    /**
     * 更新用户状态
     * @param user
     */
    @Update("update user set stutus = #{stutus} where userName = #{user}")
    void updateStutas(String user,Long stutus);

    @Delete("delete from user where userName = #{userName}")
    Long deleteUser(String userName);

//    /**
//     * 根据用户名查询用户状态
//     * @param userName
//     * @return
//     */
//    @Select("select stutus from user where userName = #{userName}")
//    Long selectUserStutas(String userName);
}
