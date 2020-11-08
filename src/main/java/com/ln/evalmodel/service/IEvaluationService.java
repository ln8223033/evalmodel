package com.ln.evalmodel.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ln.evalmodel.model.Evaluation;
import com.ln.evalmodel.model.IndexScore;
import com.ln.evalmodel.model.vo.AddEvaluationVo;
import com.ln.evalmodel.model.vo.EvaluationDetailVo;
import com.ln.evalmodel.model.vo.ListEvaluationReq;
import com.ln.evalmodel.model.vo.ListEvaluationResp;

import java.util.List;

/**
 * @Author liuN
 * @Date 2020/10/25 0025 3:30
 * @Version 1.0
 */
public interface IEvaluationService extends IService<Evaluation> {
    void saveEvaluation(AddEvaluationVo evaluationVo);

    List<ListEvaluationResp> getList(Page<ListEvaluationResp> page, ListEvaluationReq req);

    EvaluationDetailVo getDetail(Long id);

    EvaluationDetailVo calculate(List<IndexScore> indexScores);
}
