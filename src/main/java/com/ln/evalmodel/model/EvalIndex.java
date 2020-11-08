package com.ln.evalmodel.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ln.evalmodel.common.groups.New;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author liuN
 * @Date 2020/10/14 0014 23:18
 * @Version 1.0
 */
@Data
@TableName("eval_index")
@ApiModel(value = "评价指标", description = "评价指标")
public class EvalIndex extends Model<EvalIndex> {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    //模型id
    @ApiModelProperty(value = "模型id", name = "evalModelId", required = true, dataType = "long")
    @NotNull(message = "模型id不能为空！", groups = {New.class})
    @TableField("eval_model_id")
    private Long evalModelId;

    //指标父id
    @ApiModelProperty(value = "指标父id", name = "evalModelId", required = true, dataType = "long")
    @NotNull(message = "指标父id不能为空！如果无传0", groups = {New.class})
    @TableField("parent_id")
    private Long parentId;

    //指标名称
    @ApiModelProperty(value = "指标名称", name = "name", required = true, dataType = "string")
    @NotBlank(message = "指标名称不能为空！", groups = {New.class})
    private String name;


    // 指标排序号
    @ApiModelProperty(value = "指标排序号", name = "num", required = true, dataType = "int")
    @NotNull(message = "指标名称不能为空！", groups = {New.class})
    private Integer num;

    //创建日期
    @TableField("create_time")
    private Date createTime;

   //指标级别
    private Integer levels;

    //是否为叶子节点
    @TableField("leaf_node")
    private Boolean leafNode;

    //权值
    private double weight;


    //url地址
    private String url;



    @Override
    protected Serializable pkVal() {
        return null;
    }
}
