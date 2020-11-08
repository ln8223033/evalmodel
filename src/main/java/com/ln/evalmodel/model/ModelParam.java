package com.ln.evalmodel.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ln.evalmodel.common.groups.New;
import com.ln.evalmodel.common.groups.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author liuN
 * @Date 2020/10/6 0006 16:22
 * @Version 1.0
 */
@Data
@ApiModel(value = "模型参数", description = "模型参数")
public class ModelParam extends Model<ModelParam> {

    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(message = "id不能为空！", groups = {Update.class})
    private Long id;

    //模型id
    @TableField("eval_model_id")
    @NotNull(message = "模型id能为空！", groups = {New.class})
    private Long evalModelId;
    //品类编码
    @ApiModelProperty(value = "参数编码", name = "code", required = true, dataType = "string")
    @NotBlank(message = "参数编码不能为空！", groups = {New.class})
    private String code;

    //参数名称
    @ApiModelProperty(value = "参数名称", name = "name", required = true, dataType = "string")
    @NotBlank(message = "参数名称不能为空！", groups = {New.class})
    private String name;
    //创建日期
    @TableField("create_time")
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return null;
    }
}
