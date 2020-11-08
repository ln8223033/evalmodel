package com.ln.evalmodel.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author liuN
 * @Date 2020/10/26 0026 1:09
 * @Version 1.0
 * 指标得分
 */
@Data
@TableName("index_score")
public class IndexScore extends Model<IndexScore> {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    //评价id
    @TableField("evaluation_id")
    private Long evaluationId;
    //指标id
    @TableField("eval_index_id")
    private Long evalIndexId;

    //得分
    private Double score;


    @Override
    protected Serializable pkVal() {
        return null;
    }
}
