package com.zcf.world.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
* @author 许宝予
* @date 2019/04/24
*/
@Data
@Table(name = "play_options")
@ApiModel(value = "游戏内容选项模型", description = "游戏内容选项信息")
public class PlayOptions{
    @Id
    @ApiModelProperty(value = "主键", required = true, position = 1)
    private Integer id;
    @ApiModelProperty(value = "标题(如：冠亚和)", position = 2)
    private String titles;
    @ApiModelProperty(value = "内容(如：冠军大)", position = 3)
    private String contents;
    @ApiModelProperty(value = "赔率", position = 4)
    private double odds;
    @ApiModelProperty(value = "类型(1.两面 2.1~5名 3.6~10名 4.冠亚和)", position = 5)
    private String types;
    @ApiModelProperty(value = "添加时间", position = 6)
    private Date creatTime;
    @ApiModelProperty(value = "修改时间", position = 7)
    private Date updateTime;
    @ApiModelProperty(value = "是否删除N未删Y已删", position = 8)
    private String deleted;
    @ApiModelProperty(value = "备注", position = 9)
    private String remark;
}
