package com.ln.evalmodel.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author liuN
 * @Date 2020/10/15 0015 3:08
 * @Version 1.0
 */
@Data
public class ListEvalIndexResp implements Serializable {

    private Long id;
    //模型id
    private Long evalModelId;

    //指标父id
    private Long parentId;

    //指标名称
    private String name;


    // 指标排序号
    private Integer num;

    //创建日期
    private Date createTime;

    //权值
    private BigDecimal weight;



}
