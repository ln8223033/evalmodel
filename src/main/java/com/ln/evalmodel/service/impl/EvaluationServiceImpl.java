package com.ln.evalmodel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ln.evalmodel.dao.EvalIndexMapper;
import com.ln.evalmodel.dao.EvaluationMapper;
import com.ln.evalmodel.dao.EvaluationParamMapper;
import com.ln.evalmodel.dao.IndexScoreMapper;
import com.ln.evalmodel.model.EvalIndex;
import com.ln.evalmodel.model.Evaluation;
import com.ln.evalmodel.model.EvaluationParam;
import com.ln.evalmodel.model.IndexScore;
import com.ln.evalmodel.model.vo.*;
import com.ln.evalmodel.service.IEvaluationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author liuN
 * @Date 2020/10/20 0020 3:31
 * @Version 1.0
 */
@Service
public class EvaluationServiceImpl extends ServiceImpl<EvaluationMapper, Evaluation> implements IEvaluationService {
    @Autowired
    private EvaluationMapper evaluationMapper;
    @Autowired
    private EvaluationParamMapper evaluationParamMapper;
    @Autowired
    private EvalIndexMapper evalIndexMapper;
    @Autowired
    private IndexScoreMapper indexScoreMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveEvaluation(AddEvaluationVo evaluationVo) {
        Evaluation evaluation = new Evaluation();
        BeanUtils.copyProperties(evaluationVo, evaluation);
        evaluation.setCreateTime(new Date());
        evaluationMapper.insert(evaluation);
        if (evaluationVo.getParams() != null && evaluationVo.getParams().size() > 0) {
            for (EvaluationParam evaluationParam : evaluationVo.getParams()) {
                evaluationParam.setEvaluationId(evaluation.getId());
                evaluationParamMapper.insert(evaluationParam);
            }
        }
        List<EvalIndex> evalIndexList = evalIndexMapper.selectList(new QueryWrapper<EvalIndex>()
                .eq("eval_model_id", evaluation.getEvalModelId()).groupBy("parent_id", "num"));
        if (evalIndexList.size() > 0) {
            for (EvalIndex evalIndex : evalIndexList) {
                IndexScore indexScore = new IndexScore();
                indexScore.setEvalIndexId(evalIndex.getId());
                indexScore.setEvaluationId(evaluation.getId());
                indexScoreMapper.insert(indexScore);
            }
        }
    }

    @Override
    public List<ListEvaluationResp> getList(Page<ListEvaluationResp> page, ListEvaluationReq req) {
        List<ListEvaluationResp> evaluationRespList = evaluationMapper.getList(page, req);
        if (evaluationRespList.size() > 0) {
            for (ListEvaluationResp evaluationResp : evaluationRespList) {
                String evaluationObject = "";
                List<EvaluationParam> evaluationParamList = evaluationParamMapper.selectList(new QueryWrapper<EvaluationParam>()
                        .eq("evaluation_id", evaluationResp.getId()));
                for (int i = 0; i < evaluationParamList.size(); i++) {
                    evaluationObject += evaluationParamList.get(i).getName() + ",";
                }
                evaluationResp.setEvaluationObject(evaluationObject);
            }
        }
        return evaluationRespList;

    }

    @Override
    public EvaluationDetailVo getDetail(Long id) {
        Evaluation evaluation = evaluationMapper.selectById(id);
        List<IndexScoreDetailVo> indexScoreDetailList = indexScoreMapper.getIndexScoreDetailList(id, evaluation.getEvalModelId(), Long.valueOf(0));
        for (IndexScoreDetailVo indexScoreDetailVo : indexScoreDetailList) {
            if (!indexScoreDetailVo.getLeafNode()) {
                List<IndexScoreDetailVo> childList =getChildren(id, evaluation.getEvalModelId(), indexScoreDetailVo.getIndexId());
                indexScoreDetailVo.setChildIndexScoreDetailVoList(childList);
            }
        }
        EvaluationDetailVo evaluationDetailVo = new EvaluationDetailVo();
        evaluationDetailVo.setId(id);
        evaluationDetailVo.setEvalModelId(evaluation.getEvalModelId());
        evaluationDetailVo.setIndexScoreDetailVoList(indexScoreDetailList);
        return evaluationDetailVo;
    }

    @Override
    public EvaluationDetailVo calculate(List<IndexScore> indexScores) {
        return null;
    }

    private List<IndexScoreDetailVo> getChildren(Long id, Long evalModelId,Long parentId ) {
            List<IndexScoreDetailVo> childList = indexScoreMapper.getIndexScoreDetailList(id, evalModelId, parentId);
            //递归调用
            for (IndexScoreDetailVo indexScoreDetailVo : childList) {
                if (!indexScoreDetailVo.getLeafNode()) {
                    List<IndexScoreDetailVo> childList1 =getChildren(id, evalModelId, indexScoreDetailVo.getIndexId());
                    indexScoreDetailVo.setChildIndexScoreDetailVoList(childList1);
                }
            }
            // 如果当前节点无子节点数据添加空数据，递归退出
            if (childList.size() == 0) {
                return new ArrayList<>();
            }
            // 返回最终的子节点数据
            return childList;
        }
}
