package com.ln.evalmodel.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ln.evalmodel.common.groups.New;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author liuN
 * @Date 2020/10/4  13:07
 * @Version 1.0
 */
@Data
@TableName("eval_model")
public class EvalModel extends Model<EvalModel> {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    //模型名称
    @ApiModelProperty(value = "模型名称", name = "name", required = true, dataType = "string")
    @NotBlank(message = "模型名称不能为空！", groups = {New.class})
    private String name;

    //创建日期
    @TableField("create_time")
    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return null;
    }
}
