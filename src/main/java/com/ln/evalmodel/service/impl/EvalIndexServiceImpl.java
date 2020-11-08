package com.ln.evalmodel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ln.evalmodel.ahp.AHPComputeWeight;
import com.ln.evalmodel.ahp.AHPConsistency;
import com.ln.evalmodel.dao.EvalIndexMapper;
import com.ln.evalmodel.dao.JudgmentMatrixMapper;
import com.ln.evalmodel.model.EvalIndex;
import com.ln.evalmodel.model.JudgmentMatrix;
import com.ln.evalmodel.model.vo.ListEvalIndexReq;
import com.ln.evalmodel.model.vo.ListEvalIndexResp;
import com.ln.evalmodel.node.ZTreeNode;
import com.ln.evalmodel.service.IEvalIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author liuN
 * @Date 2020/10/14 0014 23:30
 * @Version 1.0
 */
@Service
public class EvalIndexServiceImpl extends ServiceImpl<EvalIndexMapper, EvalIndex> implements IEvalIndexService {
    @Autowired
    private EvalIndexMapper evalIndexMapper;
    @Autowired
    private JudgmentMatrixMapper judgmentMatrixMapper;

    @Override
    public List<ListEvalIndexResp> getList(Page<ListEvalIndexResp> page, ListEvalIndexReq req) {
        return evalIndexMapper.getList(page,req);
    }

    @Override
    public List<ZTreeNode> indexTreeList(ListEvalIndexReq req) {
        return evalIndexMapper.indexTreeList(req);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveEvalIndex(EvalIndex evalIndex) {
        if (evalIndex.getParentId()==0){
            evalIndex.setLevels(0);
        }else {
            EvalIndex parentEvalIndex = evalIndexMapper.selectById(evalIndex.getParentId());
            evalIndex.setLevels(parentEvalIndex.getLevels()+1);
            parentEvalIndex.setLeafNode(false);
            evalIndexMapper.updateById(parentEvalIndex);
        }
        evalIndex.setLeafNode(true);
        evalIndex.setCreateTime(new Date());
        evalIndexMapper.insert(evalIndex);
    }

    @Override
    public List<JudgmentMatrix> saveJudgmentMatrix(Long parentEvalIndexId, Long evalModelId,List<EvalIndex> evalIndexList) {
        List<JudgmentMatrix>  judgmentMatrixList =  judgmentMatrixMapper.selectList(new QueryWrapper<JudgmentMatrix>().eq("parent_eval_index_id",parentEvalIndexId).eq("eval_model_id",evalModelId));
        if (judgmentMatrixList.size()==0) {
            for (int i = 0; i < evalIndexList.size(); i++) {
                for (int j = 0; j < evalIndexList.size(); j++) {
                    JudgmentMatrix judgmentMatrix = new JudgmentMatrix();
                    judgmentMatrix.setTrain(i);
                    judgmentMatrix.setRow(j);
                    judgmentMatrix.setRowEvalIndexId(evalIndexList.get(i).getId());
                    judgmentMatrix.setColumnEvalIndexId(evalIndexList.get(j).getId());
                    judgmentMatrix.setParentEvalIndexId(parentEvalIndexId);
                    judgmentMatrix.setEvalModelId(evalModelId);
                    if (evalIndexList.get(i).getId().equals(evalIndexList.get(j).getId())){
                        judgmentMatrix.setValue(1.0);

                    }
                    judgmentMatrixMapper.insert(judgmentMatrix);
                }
            }
            List<JudgmentMatrix>  newJudgmentMatrixList =  judgmentMatrixMapper.selectList(new QueryWrapper<JudgmentMatrix>().eq("parent_eval_index_id",parentEvalIndexId).eq("eval_model_id",evalModelId));
            return newJudgmentMatrixList;
        }else {
            return judgmentMatrixList;
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Map<String, Integer> calculate(Long parentEvalIndexId, Long evalModelId, List<JudgmentMatrix> judgmentMatrixList) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        List<EvalIndex> evalIndexList = evalIndexMapper.selectList(new QueryWrapper<EvalIndex>().eq("parent_id", parentEvalIndexId).eq("eval_model_id",evalModelId).groupBy("parent_id","num"));

        double[][] matrix = new double[evalIndexList.size()][evalIndexList.size()];
        for (JudgmentMatrix judgmentMatrix:judgmentMatrixList){
                matrix[judgmentMatrix.getRow()][judgmentMatrix.getTrain()]=judgmentMatrix.getValue();
            judgmentMatrixMapper.updateById(judgmentMatrix);
        }
        double[] weight =new double[]{};
        AHPComputeWeight ahpComputeWeight = new AHPComputeWeight();
        Map<String, Object> map1 = ahpComputeWeight.AHPComputeWeight(matrix);
        weight = (double[]) map1.get("weight");
        double CR = (double) map1.get("CR");
        if (CR<0.10){
            for (int i=0;i<weight.length;i++){
                evalIndexList.get(i).setWeight(weight[i]);
                evalIndexMapper.updateById(evalIndexList.get(i));
            }
            map.put("result",1);
            return map;
        }else {
             map =new AHPConsistency().AHPConsistency(matrix);
            map.put("result",0);
            return map;
        }
        
    }
}


