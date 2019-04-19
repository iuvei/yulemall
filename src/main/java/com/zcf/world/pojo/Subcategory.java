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
@Table(name = "subcategory")
@ApiModel(value = "二级分类模型", description = "二级分类信息")
public class Subcategory{
    @Id
    @ApiModelProperty(value = "主键", required = true, position = 1)
    private Integer id;
    @ApiModelProperty(value = "一级分类id", position = 2)
    private Integer categoryId;
    @ApiModelProperty(value = "二级分类名称", position = 3)
    private String subcategory;
    @ApiModelProperty(value = "二级分类图片", position = 4)
    private String img;
    @ApiModelProperty(value = "创建时间", position = 5)
    private Date creatTime;
    @ApiModelProperty(value = "修改时间", position = 6)
    private Date updateTime;
    @ApiModelProperty(value = "是否删除N未删Y已删", position = 7)
    private String deleted;
    @ApiModelProperty(value = "是否显示在首页(0不显示1显示)", position = 8)
    private String showHome;
    @ApiModelProperty(value = "备注", position = 9)
    private String remark;
}
