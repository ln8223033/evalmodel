package com.ln.evalmodel.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author liuN
 * @Date 2020/10/26 0026 2:06
 * @Version 1.0
 */
@Data
public class EvaluationDetailVo implements Serializable {
    private Long id;
    private Long evalModelId;
    private List<IndexScoreDetailVo> indexScoreDetailVoList;
}
