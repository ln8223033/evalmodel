package com.ln.evalmodel.model.vo;

import com.ln.evalmodel.model.Evaluation;
import com.ln.evalmodel.model.EvaluationParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author liuN
 * @Date 2020/10/25 0025 3:35
 * @Version 1.0
 */
@Data
public class AddEvaluationVo extends Evaluation {
    @ApiModelProperty(value = "评价参数",dataType = "List")
    private List<EvaluationParam> params ;
}
