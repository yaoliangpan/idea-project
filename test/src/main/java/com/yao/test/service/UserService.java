package com.yao.test.service;
import com.yao.test.controller.PageQueryVo;
import com.yao.test.controller.PageResultVo;
import com.yao.test.controller.vo;


public interface UserService {
    /**
     * 分页查询用户
     * @param page
     * @return
     */
     PageResultVo selectUser(PageQueryVo page);

    /**
     *  添加用户
     * @param user
     */
    Long addUser(vo user);

    /**
     *  登录
     * @param user
     * @param password
     */
    Long login(String user, Long password);

    /**
     * 修改状态
     * @param userName
     */
    void setStuta(String userName);

    /**
     * 删除用户
     * @param userName
     * @return
     */
    Long deleteUser(String userName);
}
