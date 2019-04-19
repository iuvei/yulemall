package com.zcf.world.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
* @author 许宝予
* @date 2019/04/19
*/
@Data
@Table(name = "searchs")
@ApiModel(value = "搜索记录表模型", description = "搜索记录表信息")
public class Searchs{
    @Id
    @ApiModelProperty(value = "主键", required = true, position = 1)
    private Integer id;
    @ApiModelProperty(value = "用户id", position = 2)
    private Integer userId;
    @ApiModelProperty(value = "搜索内容", position = 3)
    private String searchs;
    @ApiModelProperty(value = "创建时间", position = 4)
    private Date creatTime;
    @ApiModelProperty(value = "修改时间", position = 5)
    private Date updateTime;
    @ApiModelProperty(value = "是否删除N未删Y已删", position = 6)
    private String deleted;
    @ApiModelProperty(value = "备注", position = 7)
    private String remark;
}
