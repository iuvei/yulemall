package com.zcf.world.service;

import com.zcf.world.common.exception.CommonException;
import com.zcf.world.common.exception.ExceptionEnum;
import com.zcf.world.common.utils.Body;
import com.zcf.world.common.utils.RandomUtils;
import com.zcf.world.common.utils.SmsUtils;
import com.zcf.world.mapper.UserMapper;
import com.zcf.world.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
* @author 许宝予
* @date 2019/04/15
*/
@Slf4j
@Service
public class UserService{

    @Resource
    private UserMapper usermapper;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 设置RedisKey的前缀
     */
    private static final String PHONE_NUMBER = "PHONE_NUMBER:";

    //注册时的验证码
    public Body getRegisterCode(String phone) {

        if (StringUtils.isEmpty(phone)) {
            throw new CommonException(ExceptionEnum.PHONE_NUMBER_BE_NULL);
        }
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("phone",phone);
        List<User> list = this.usermapper.selectByExample(example);

        //如果信息不为空表示改手机号已被注册过
        if (list.size() != 0) {
            throw new CommonException(ExceptionEnum.PHONE_NUMBER_IS_REGISTERED);
        }
        //获取随机数
        String random = RandomUtils.Random();
        SmsUtils.sendRegister(phone, random);
        log.error("注册时的验证码*********************************" + random);
        log.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<" + phone);
        //把验证码放入Redis中并设置3分钟的过期时间
        redisTemplate.opsForValue().set(PHONE_NUMBER + phone, random, 60 * 3, TimeUnit.SECONDS);
        return Body.newInstance(200,"验证码已发送，请注意查收");
    }

    /**
     * 新增一条数据
     *
     * @param user user对象
     */
    public void addUser(User user) {
        if (user.getCreatTime() == null){
            user.setCreatTime(new Date());
        }
        if (user.getUpdateTime() == null){
            user.setUpdateTime(new Date());
        }
        if (user.getDeleted() == null){
            user.setDeleted("N");
        }
        int count = this.usermapper.insertSelective(user);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteUserById(Integer id) {

        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("id",id);
        List<User> list = this.usermapper.selectByExample(example);
        if (list.size() != 1){
            throw new CommonException(ExceptionEnum.NULL_LIST);
        }
        User user = new User();
        user.setId(list.get(0).getId());
        user.setDeleted("Y");
        user.setUpdateTime(new Date());
        updateUser(user);
    }

    /**
     * 根据主键更新非空数据
     *
     * @param user user对象
     */
    public void updateUser(User user) {
        user.setUpdateTime(new Date());
        int count = this.usermapper.updateByPrimaryKeySelective(user);
         if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return User对象集合
     */
    public List<User> getAllUser() {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("deleted","N");
        List<User> list = this.usermapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return User对象
     */
    public User getUser(Integer id){
        User User = this.usermapper.selectByPrimaryKey(id);
        if (User == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return User;
    }

    /**
     * 登录
     */
    public Body login(String phone, String password){
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("phone", phone)
                .andEqualTo("loginPwd",password)
                .andEqualTo("deleted","N");
        List<User> list = this.usermapper.selectByExample(example);
        if (list.size() != 1){
            return Body.newInstance(100,"用户名或密码错误");
        }
        return Body.newInstance(list);
    }

    /**
     * 注册
     */
    public Body insertOneUser(String phone,String password,String code){

        //取redis里面的  验证码
        String codes = (String) redisTemplate.opsForValue().get(PHONE_NUMBER + phone);
        if (!StringUtils.equals(codes, code)) {
            return Body.newInstance(100,"验证码不匹配，或已过期。请重新获取");
        }

        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("phone", phone)
                .andEqualTo("deleted","N");
        List<User> list = this.usermapper.selectByExample(example);
        if (list.size()>0){
            return Body.newInstance(100,"该用户已存在，如若忘记密码请找回");
        }

        User user = new User();
        user.setPhone(phone);
        user.setLoginPwd(password);
        user.setCreatTime(new Date());
        user.setUpdateTime(new Date());
        user.setDeleted("N");
        int count = this.usermapper.insertSelective(user);
        if (count == 1){
            return Body.newInstance(200,"注册成功");
        }else {
            return Body.newInstance(100,"注册失败");
        }
    }
    /**
     * 验证码登录
     */
    public Body codeLogin(String phone,String code){

        //取redis里面的  验证码
        String codes = (String) redisTemplate.opsForValue().get(PHONE_NUMBER + phone);
        if (!StringUtils.equals(codes, code)) {
            return Body.newInstance(100,"验证码不匹配，或已过期。请重新获取");
        }
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("phone", phone)
                .andEqualTo("deleted","N");
        List<User> list = this.usermapper.selectByExample(example);
        if (list.size()>0){
            return Body.newInstance(list);
        }else {
            return Body.newInstance(100,"该用户不存在");
        }
    }

    /**
     * 忘记密码
     */
    public Body updateLogin(String phone,String pwd,String code){

        //取redis里面的  验证码
        String codes = (String) redisTemplate.opsForValue().get(PHONE_NUMBER + phone);
        if (!StringUtils.equals(codes, code)) {
            return Body.newInstance(100,"验证码不匹配，或已过期。请重新获取");
        }

        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("phone", phone)
                .andEqualTo("deleted","N");
        List<User> list = this.usermapper.selectByExample(example);
        if (list.size()>0){
            int i = usermapper.updateLoginPwd(phone,pwd);
            if (i>0){
                return Body.newInstance(200,"新密码设置成功");
            }else {
                return Body.newInstance(100,"未知错误，请联系管理员");
            }
        }else {
            return Body.newInstance(100,"该用户不存在");
        }
    }


}
