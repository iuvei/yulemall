package com.zcf.world.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
* @author 许宝予
* @date 2019/04/15
*/
@Data
@Table(name = "user")
@ApiModel(value = "用户表模型", description = "用户表信息")
public class User{
    @Id
    @ApiModelProperty(value = "主键", required = true, position = 1)
    private Integer id;
    @ApiModelProperty(value = "昵称", position = 2)
    private String nickname;
    @ApiModelProperty(value = "手机号", position = 3)
    private String phone;
    @ApiModelProperty(value = "登录密码", position = 4)
    private String loginPwd;
    @ApiModelProperty(value = "支付密码", position = 5)
    private String payPwd;
    @ApiModelProperty(value = "账户余额", position = 6)
    private Date money;
    @ApiModelProperty(value = "用户头像", position = 7)
    private String img;
    @ApiModelProperty(value = "创建时间", position = 8)
    private Date creatTime;
    @ApiModelProperty(value = "修改时间", position = 9)
    private Date updateTime;
    @ApiModelProperty(value = "是否删除(N未删除Y已删)", position = 10)
    private String deleted;
    @ApiModelProperty(value = "备注", position = 11)
    private String remark;
}
