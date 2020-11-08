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

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author liuN
 * @Date 2020/10/18 0025 3:14
 * @Version 1.0
 */
@Data
@TableName("evaluation")
@ApiModel(value = "评价", description = "评价")
public class Evaluation extends Model<Evaluation> {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    //模型id
    @ApiModelProperty(value = "模型id", name = "evalModelId", required = true, dataType = "long")
    @NotNull(message = "模型id不能为空！", groups = {New.class})
    @TableField("eval_model_id")
    private Long evalModelId;
    //创建日期
    @TableField("create_time")
    private Date createTime;
    //评价结果
    private Double result;
    @Override
    protected Serializable pkVal() {
        return null;
    }
}
