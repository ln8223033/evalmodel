package com.ln.evalmodel.model.vo;

import com.ln.evalmodel.model.EvalModel;
import com.ln.evalmodel.model.ModelParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author liuN
 * @Date 2020/10/6 0006 16:37
 * @Version 1.0
 */
@Data
public class AddEvalModelVo  extends EvalModel {

    @ApiModelProperty(value = "模型参数",dataType = "List")
    private List<ModelParam> modelParams ;


}
