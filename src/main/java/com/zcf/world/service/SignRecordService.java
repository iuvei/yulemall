package com.zcf.world.service;

import com.zcf.world.common.utils.Body;
import com.zcf.world.mapper.UserMapper;
import com.zcf.world.pojo.SignRecord;
import com.zcf.world.mapper.SignRecordMapper;
import com.zcf.world.pojo.User;
import org.springframework.scheduling.annotation.Scheduled;
import tk.mybatis.mapper.common.example.SelectByExampleMapper;
import tk.mybatis.mapper.entity.Example;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import com.zcf.world.common.exception.CommonException;
import com.zcf.world.common.exception.ExceptionEnum;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.zcf.world.common.utils.DataTimeUtils.parse2Date;
import static com.zcf.world.common.utils.DataTimeUtils.parseLocalDateTime;

/**
 * @author 许宝予
 * @date 2019/04/23
 */
@Service
public class SignRecordService {

    @Resource
    private SignRecordMapper signRecordmapper;
    @Resource
    private UserMapper userMapper;

    /**
     * 新增一条数据
     *
     * @param signRecord signRecord对象
     */
    public void addSignRecord(SignRecord signRecord) {
        if (signRecord.getCreatTime() == null) {
            signRecord.setCreatTime(new Date());
        }
        if (signRecord.getUpdateTime() == null) {
            signRecord.setUpdateTime(new Date());
        }
        signRecord.setDeleted("N");
        int count = this.signRecordmapper.insertSelective(signRecord);
        if (count != 1) {
            throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteSignRecordById(Integer id) {

        Example example = new Example(SignRecord.class);
        example.createCriteria().andEqualTo("id", id);
        List<SignRecord> list = this.signRecordmapper.selectByExample(example);
        if (list.size() != 1) {
            throw new CommonException(ExceptionEnum.NULL_LIST);
        }
        SignRecord signRecord = new SignRecord();
        signRecord.setId(list.get(0).getId());
        signRecord.setDeleted("Y");
        signRecord.setUpdateTime(new Date());
        updateSignRecord(signRecord);
    }

    /**
     * 根据主键更新非空数据
     *
     * @param signRecord signRecord对象
     */
    public void updateSignRecord(SignRecord signRecord) {
        signRecord.setUpdateTime(new Date());
        int count = this.signRecordmapper.updateByPrimaryKeySelective(signRecord);
        if (count != 1) {
            throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return SignRecord对象集合
     */
    public List<SignRecord> getAllSignRecord() {
        Example example = new Example(SignRecord.class);
        example.createCriteria().andEqualTo("deleted", "N");
        List<SignRecord> list = this.signRecordmapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return SignRecord对象
     */
    public SignRecord getSignRecord(Integer id) {
        SignRecord SignRecord = this.signRecordmapper.selectByPrimaryKey(id);
        if (SignRecord == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return SignRecord;
    }

    /**
     * 判断当前时间 是否为每月的 一号  判断当前月 有多少天
     */
    public Body updateDayOrMonth() {
        //获取今天的日期时间
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        //截取   换   插入日期
        String substring = dateString.substring(8, 10);
        String year = dateString.substring(0, 4);
        String yue = dateString.substring(5, 7);
        String month;
        //如果 月份为01-09 时  去掉前面的0
        if (yue.substring(0, 1).equals("0")) {
            month = yue.substring(1, 2);
        } else {
            month = yue;
        }

        //判断当前日期是不是每月的 一号
        if (substring.equals("01")) {
            //获取 当前月有几天
            Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
            int day = aCalendar.getActualMaximum(Calendar.DATE);
            //取
            Example example = new Example(User.class);
            example.createCriteria().andEqualTo("deleted", "N");
            List<User> list = this.userMapper.selectByExample(example);
            //每个用户
            for (int i = 0; i < list.size(); i++) {
                //本月天数
                for (int y = 1; y <= day; y++) {
                    String abc = String.valueOf(y);
                    //塞数据
                    SignRecord signRecord = new SignRecord();
                    signRecord.setUserId(list.get(i).getId());
                    signRecord.setType("2");
                    signRecord.setDeleted("N");
                    signRecord.setYears(year);
                    signRecord.setMonths(month);
                    signRecord.setDays(abc);
                    signRecord.setCreatTime(new Date());
                    signRecord.setUpdateTime(new Date());
                    int insert = signRecordmapper.insert(signRecord);
                    if (insert < 0) {
                        return Body.newInstance(100, "数据插入错误");
                    }
                }
            }
            //初始化之后清空上月记录
            //如果是1月时   赋值  month 为13
            if (month.equals("1")){
                month = "13";
            }
            signRecordmapper.deleteSignRecord(String.valueOf((Integer.parseInt(month) - 1)));
            return Body.newInstance(200, "月签到数据初始化成功");
        } else {
            return Body.newInstance(100, "今日非月初");
        }
    }

    /**
     * 每次签到  修改状态为 已签到
     */
    public Body updateTypeOfSign(Integer id) {
         //id 查询 记录  type = 2 的
        Example example = new Example(SignRecord.class);
        example.createCriteria().andEqualTo("deleted", "N")
                .andEqualTo("id",id);
        List<SignRecord> list = this.signRecordmapper.selectByExample(example);
        if (list.size()>0){
            for (int i = 0;i<list.size();i++){
                if (list.get(i).getType().equals("2")){
                    int a = signRecordmapper.updateTypeOfSign(id);
                    if (a>0){
                        return Body.newInstance(200,"签到成功");
                    }else {
                        return Body.newInstance(100,"签到失败");
                    }
                }else {
                    return Body.newInstance(100,"您已签到,请勿重复签到!");
                }
            }
        }
        return Body.newInstance(100,"未知错误,請聯繫管理員");
    }

    /**
     *   每天23:58分执行  修改所有未签到的用户都为断签
     */
    public Body updateTypeDQ(){
        //获取当前 日期    天
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String substring = dateString.substring(8, 10);
        String days;
        //如果为  01  -  09  号则截取 为1-9号
        if (substring.substring(0, 1).equals("0")) {
            days = substring.substring(1, 2);
        } else {
            days = substring;
        }
        //查询所有用户
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("deleted", "N");
        List<User> list = this.userMapper.selectByExample(example);
        if (list.size()>0){
            for (int i = 0;i<list.size();i++){
                //  查询  用户签到表  查看所有未签到记录(即初始化记录)
                Example ex = new Example(SignRecord.class);
                ex.createCriteria().andEqualTo("deleted", "N")
                            .andEqualTo("type","2")
                            .andEqualTo("days",days);
                List<SignRecord> signRecords = this.signRecordmapper.selectByExample(ex);
                if (signRecords.size()>0){
                    for (int y = 0; y<signRecords.size();y++){
                        //依次修改为断签
                        int result = signRecordmapper.updateTypeDQ(signRecords.get(y).getId());
                        if (result>0){
                            return Body.newInstance(200,"时间已到，未签到用户设为断签");
                        }
                    }
                }
            }
        }
        return Body.newInstance(100);
    }
}
