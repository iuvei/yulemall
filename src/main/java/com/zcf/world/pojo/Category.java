package com.zcf.world.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
* @author 许宝予
* @date 2019/04/17
*/
@Data
@Table(name = "category")
@ApiModel(value = "一级分类模型", description = "一级分类信息")
public class Category{
    @Id
    @ApiModelProperty(value = "主键", required = true, position = 1)
    private Integer id;
    @ApiModelProperty(value = "一级分类", position = 2)
    private String parentCategory;
    @ApiModelProperty(value = "创建时间", position = 3)
    private Date creatTime;
    @ApiModelProperty(value = "修改时间", position = 4)
    private Date updateTime;
    @ApiModelProperty(value = "是否删除N未删Y已删", position = 5)
    private String deleted;
    @ApiModelProperty(value = "备注", position = 6)
    private String remark;
}
