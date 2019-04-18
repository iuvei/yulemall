package com.zcf.world.controller.api;


import com.zcf.world.common.utils.Body;
import com.zcf.world.pojo.User;
import com.zcf.world.service.UserService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import java.util.List;

/**
* @author 许宝予
* @date 2019/04/15
*/
@RestController
@RequestMapping("user")
@Api(value = "用户表控制器", tags = {"用户表接口"})
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "新增")
    @PostMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", dataType = "Integer"),
                @ApiImplicitParam(name = "nickname", value = "昵称", dataType = "String"),
                @ApiImplicitParam(name = "phone", value = "手机号", dataType = "String"),
                @ApiImplicitParam(name = "loginPwd", value = "登录密码", dataType = "String"),
                @ApiImplicitParam(name = "payPwd", value = "支付密码", dataType = "String"),
                @ApiImplicitParam(name = "money", value = "账户余额", dataType = "Date"),
                @ApiImplicitParam(name = "img", value = "头像", dataType = "String"),
                @ApiImplicitParam(name = "creatTime", value = "创建时间", dataType = "Date"),
                @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
                @ApiImplicitParam(name = "deleted", value = "是否删除(N未删除Y已删)", dataType = "String"),
                @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> addUser(User user) {
        this.userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value="/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer id) {
        this.userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改")
    @PutMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", dataType = "Integer"),
            @ApiImplicitParam(name = "nickname", value = "昵称", dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "手机号", dataType = "String"),
            @ApiImplicitParam(name = "loginPwd", value = "登录密码", dataType = "String"),
            @ApiImplicitParam(name = "payPwd", value = "支付密码", dataType = "String"),
            @ApiImplicitParam(name = "money", value = "账户余额", dataType = "Date"),
            @ApiImplicitParam(name = "img", value = "头像", dataType = "String"),
            @ApiImplicitParam(name = "creatTime", value = "创建时间", dataType = "Date"),
            @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "Date"),
            @ApiImplicitParam(name = "deleted", value = "是否删除(N未删除Y已删)", dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    public ResponseEntity<Void> updateUser(User user) {
        this.userService.updateUser(user);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取单个")
    @PostMapping(value = "getUser",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<User> getUser(Integer id) {
        return ResponseEntity.ok(this.userService.getUser(id));
    }

    @ApiOperation(value = "获取所有")
    @GetMapping(produces = {"application/json;charset=UTF-8"})
    public  ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(this.userService.getAllUser());
    }

    @ApiOperation(value = "手机号登录")
    @PostMapping(value = "login",produces = {"application/json;charset=UTF-8"})
    public Body login(String phone, String password) {
        return userService.login(phone,password);
    }

    @ApiOperation(value = "注册")
    @PostMapping(value = "insertOneUser",produces = {"application/json;charset=UTF-8"})
    public Body insertOneUser(String phone, String password,String code) {
        return userService.insertOneUser(phone,password,code);
    }

    @ApiOperation(value = "验证码登录")
    @PostMapping(value = "codeLogin",produces = {"application/json;charset=UTF-8"})
    public Body codeLogin(String phone,String code) {
        return userService.codeLogin(phone,code);
    }

    @ApiOperation(value = "忘记密码")
    @PostMapping(value = "updateLogin",produces = {"application/json;charset=UTF-8"})
    public Body updateLogin(String phone,String pwd,String code) {
        return userService.updateLogin(phone,pwd,code);
    }

    @ApiOperation(value = "获取手机验证码")
    @PostMapping(value = "getRegisterCode",produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<Void> getRegisterCode(String phone) {
        userService.getRegisterCode(phone);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
