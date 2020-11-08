package com.ln.evalmodel.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author liuN
 * @Date 2020/10/26 0026 2:10
 * @Version 1.0
 */
@Data
public class IndexScoreDetailVo implements Serializable {
    //指标得分id
    private Long id;
    //指标id
    private Long indexId;
    //模型id
    private Long evalModelId;
    //指标父id
    private Long parentId;
    //指标名称
    private String name;
    // 指标排序号
    private Integer num;
    //指标级别
    private Integer levels;
    //是否为叶子节点
    private Boolean leafNode;
    //权值
    private double weight;
    //url地址
    private String url;
    //得分
    private Double score;

    private List<IndexScoreDetailVo> childIndexScoreDetailVoList;

}
