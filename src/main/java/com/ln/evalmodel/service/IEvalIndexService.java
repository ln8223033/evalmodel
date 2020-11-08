package com.ln.evalmodel.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ln.evalmodel.model.EvalIndex;
import com.ln.evalmodel.model.JudgmentMatrix;
import com.ln.evalmodel.model.vo.ListEvalIndexReq;
import com.ln.evalmodel.model.vo.ListEvalIndexResp;
import com.ln.evalmodel.node.ZTreeNode;

import java.util.List;
import java.util.Map;

/**
 * @Author liuN
 * @Date 2020/10/14 0014 23:29
 * @Version 1.0
 */
public interface IEvalIndexService extends IService<EvalIndex> {
    List<ListEvalIndexResp> getList(Page<ListEvalIndexResp> page, ListEvalIndexReq req);

    List<ZTreeNode> indexTreeList(ListEvalIndexReq req);

    void saveEvalIndex(EvalIndex evalIndex);


    List<JudgmentMatrix> saveJudgmentMatrix(Long parentEvalIndexId,Long evalModelId, List<EvalIndex> evalIndexList);


    Map<String, Integer> calculate(Long parentEvalIndexId, Long evalModelId, List<JudgmentMatrix> judgmentMatrixList);
}
