package com.ln.evalmodel.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ln.evalmodel.common.groups.New;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author liuN
 * @Date 2020/10/19 0019 6:43
 * @Version 1.0
 * 判断矩阵
 */
@Data
@TableName("judgment_matrix")
public class JudgmentMatrix extends Model<JudgmentMatrix> {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    //父指标id
    @ApiModelProperty(value = "父指标id", name = "parentEvalIndexId", required = true, dataType = "int")
    @NotNull(message = "父指标id不能为空！", groups = {New.class})
    @TableField("parent_eval_index_id")
    private Long parentEvalIndexId;


    //模型id
    @ApiModelProperty(value = "模型id", name = "evalModelId", required = true, dataType = "long")
    @NotNull(message = "模型id不能为空！", groups = {New.class})
    @TableField("eval_model_id")
    private Long evalModelId;
    //矩阵行
    private Integer row;

    //矩阵列
    private Integer train;


    //行指标id
    @TableField("row_eval_index_id")
    private Long rowEvalIndexId;

    //列指标id
    @TableField("column_eval_index_id")
    private Long columnEvalIndexId;

    //值
    private Double value;




    @Override
    protected Serializable pkVal() {
        return null;
    }
}
