package com.ln.evalmodel.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author liuN
 * @Date 2020/10/15 0015 0:06
 * @Version 1.0
 */
@Data
public class ListModelResp implements Serializable {

    private Long id;
    //模型名称
    private String name;
    //创建日期
    private Date createTime;
    //模型参数
    private String params;
}
