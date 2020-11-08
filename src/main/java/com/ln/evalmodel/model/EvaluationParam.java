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

/**
 * @Author liuN
 * @Date 2020/10/25 0025 3:22
 * @Version 1.0
 */

@Data
@ApiModel(value = "评价参数", description = "评价参数")
@TableName("evaluation_param")
public class EvaluationParam extends Model<EvaluationParam> {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    //评价id
    @TableField("evaluation_id")
    @NotNull(message = "评价id能为空！", groups = {New.class})
    private Long evaluationId;
    //参数值
    @ApiModelProperty(value = "参数值", name = "code", required = true, dataType = "string")
    @NotBlank(message = "参数值不能为空！", groups = {New.class})
    private String code;

    //参数名称
    @ApiModelProperty(value = "参数名称", name = "name", required = true, dataType = "string")
    @NotBlank(message = "参数名称不能为空！", groups = {New.class})
    private String name;

    //参数描述
    @ApiModelProperty(value = "参数描述", name = "description", required = true, dataType = "string")
    @NotBlank(message = "参数描述不能为空！", groups = {New.class})
    private String description;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
