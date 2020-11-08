package com.ln.evalmodel.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author liuN
 * @Date 2020/10/26 0026 1:31
 * @Version 1.0
 */
@Data
public class ListEvaluationResp {

    private Long id;
    //模型id
    private Long evalModelId;
    //创建日期
    private Date createTime;
    //模型名称
    private String evalModelName;
    //评价对象
    private String evaluationObject;
    //评价结果
    private Double result;
}
