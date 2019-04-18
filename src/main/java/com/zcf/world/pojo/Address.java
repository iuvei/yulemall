package com.zcf.world.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
* @author 许宝予
* @date 2019/04/18
*/
@Data
@Table(name = "address")
@ApiModel(value = "用户收货地址表模型", description = "用户收货地址表信息")
public class Address{
    @Id
    @ApiModelProperty(value = "主键", required = true, position = 1)
    private Integer id;
    @ApiModelProperty(value = "用户id", position = 2)
    private Integer userid;
    @ApiModelProperty(value = "收货人姓名", position = 3)
    private String shouname;
    @ApiModelProperty(value = "收货人手机号", position = 4)
    private String shouphone;
    @ApiModelProperty(value = "所在地区", position = 5)
    private String shouarea;
    @ApiModelProperty(value = "详细地址", position = 6)
    private String shouaddress;
    @ApiModelProperty(value = "添加时间", position = 7)
    private Date creatTime;
    @ApiModelProperty(value = "修改时间", position = 8)
    private Date updateTime;
    @ApiModelProperty(value = "是否删除N未删Y已删", position = 9)
    private String deleted;
    @ApiModelProperty(value = "备注", position = 10)
    private String remark;
}
