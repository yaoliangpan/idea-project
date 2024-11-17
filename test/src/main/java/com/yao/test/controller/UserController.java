package com.yao.test.controller;
import com.yao.test.common.BaseContext;
import com.yao.test.properties.JwtProperties;
import com.yao.test.service.UserService;
import com.yao.test.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RequestMapping("/user")
@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final JwtProperties jwtProperties;

    private final UserService userService;

    /**
     * 登录
     * @param user
     * @param password
     * @return
     */
    @GetMapping("/login")
    public Result getHello(String user,Long password){
        log.info("登录操作：{}",user);
        Long code = userService.login(user, password);
        if(code == 0L){
            return Result.error("用户名或密码错误");
        }
        // 登录成功,生成token
        HashMap<String, Object> map = new HashMap<>();
        map.put("userName", user);
        String token = JwtUtil.createJWT(jwtProperties.getSecretKey(),
                jwtProperties.getTtl(),
                map);
        return Result.success(token);
    }

    /**
     * 分页查询用户信息
     * @param page
     * @return
     */
    @PostMapping("/page")
    public Result<PageResultVo> getPage(@RequestBody PageQueryVo page){
        log.info("分页查询：{}", page);
        System.out.println(BaseContext.getThreadLocal());
        PageResultVo pageResultVo = userService.selectUser(page);
        return Result.success(pageResultVo);
    }

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    @PostMapping("/add")
    public Result addUser(@RequestBody vo user){
        log.info("添加用户操作：{}",user);

        Long code = userService.addUser(user);
        if(code==0L){
            return Result.error("添加失败,用户已存在");
        }
        return Result.success();
    }

    /**
     * 更新用户信息(设置为离线)
     * @param userName
     */
    @GetMapping("/setStuta")
    public void test(String userName){
        log.info("{}:状态变了",userName);
        userService.setStuta(userName);
    }

    @GetMapping("/delete")
    public Result deleteUser(String userName){
        log.info("删除用户操作：{}",userName);
        Long code = userService.deleteUser(userName);
        if(code==0L){
            return Result.error("删除失败,用户不存在");
        }
        return Result.success("删除成功");
    }
}
