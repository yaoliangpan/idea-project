package com.yao.test.service.iml;

import com.yao.test.controller.PageQueryVo;
import com.yao.test.controller.PageResultVo;
import com.yao.test.mapper.UserMapper;
import com.yao.test.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.yao.test.controller.vo;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceIml  implements UserService {

    private  final UserMapper userMapper;
    @Override
    public PageResultVo selectUser(PageQueryVo page) {
        //查询总数
        Long total = userMapper.selectUserCount(page);

        page.setPage((page.getPage()-1)*page.getPageSize());

        //修改用户状态
        userMapper.updateStutas(page.getUserName(),1L);
        //分页查询
        vo[] arr= userMapper.selectUserPage(page);

        PageResultVo pageResultVo = new PageResultVo();
        pageResultVo.setRecords(arr);
        pageResultVo.setTotal(total);
        return  pageResultVo;
    }

    @Override
    public Long addUser(vo user) {
    //判断用户名是否存在
    Long id = userMapper.selectUserByName(user.getUserName());
        if(id!=0L){
        return 0L;
    }
        System.out.println(id);

    //插入用户信息
    user.setCreateDate(LocalDateTime.now());
    userMapper.addUser(user);
    return 1L;
    }

    @Override
    public Long login(String user, Long password) {

        Long code=userMapper.login(user,password);
        if(code==0L){
            return 0L;
        }else {
            //修改用户状态
            userMapper.updateStutas(user,1L);
            return 1L;
        }
    }

    @Override
    public void setStuta(String userName) {
        userMapper.updateStutas(userName,0L);
    }

    @Override
    public Long deleteUser(String userName) {

        Long code=userMapper.deleteUser(userName);

        return code;
    }


}
