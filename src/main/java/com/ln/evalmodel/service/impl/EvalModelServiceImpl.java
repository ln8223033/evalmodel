package com.ln.evalmodel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ln.evalmodel.dao.EvalModelMapper;
import com.ln.evalmodel.dao.ModelParamsMapper;
import com.ln.evalmodel.model.EvalModel;
import com.ln.evalmodel.model.ModelParam;
import com.ln.evalmodel.model.vo.AddEvalModelVo;
import com.ln.evalmodel.model.vo.ListModelReq;
import com.ln.evalmodel.model.vo.ListModelResp;
import com.ln.evalmodel.service.IEvalModelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * @Author liuN
 * @Date 2020/10/6 0006 15:53
 * @Version 1.0
 */
@Service
public class EvalModelServiceImpl extends ServiceImpl<EvalModelMapper, EvalModel> implements IEvalModelService {
    @Autowired
    private EvalModelMapper evalModelMapper;
    @Autowired
    private ModelParamsMapper modelParamsMapper;


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveEvalModel(AddEvalModelVo evalModelVo) {
        EvalModel evalModel = new EvalModel();
        BeanUtils.copyProperties(evalModelVo, evalModel);
        evalModel.setCreateTime(new Date());
        evalModelMapper.insert(evalModel);
        if (evalModelVo.getModelParams() != null && evalModelVo.getModelParams().size() > 0) {
            for (ModelParam modelParam : evalModelVo.getModelParams()) {
                modelParam.setCreateTime(new Date());
                modelParam.setEvalModelId(evalModel.getId());
                modelParamsMapper.insert(modelParam);
            }
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void editEvalModel(AddEvalModelVo evalModelVo) {
        EvalModel evalModel = new EvalModel();
        BeanUtils.copyProperties(evalModelVo, evalModel);
        evalModelMapper.updateById(evalModel);
        List<ModelParam> modelParams = modelParamsMapper.selectList(new QueryWrapper<ModelParam>().eq("eval_model_id", evalModel.getId()));
        if (modelParams != null) {
            modelParamsMapper.delete(new QueryWrapper<ModelParam>().eq("eval_model_id", evalModel.getId()));
        }
        if (evalModelVo.getModelParams() != null && evalModelVo.getModelParams().size() > 0) {
            for (ModelParam modelParam : evalModelVo.getModelParams()) {
                modelParam.setCreateTime(new Date());
                modelParam.setEvalModelId(evalModel.getId());
                modelParamsMapper.insert(modelParam);

            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void removeEvalModel(EvalModel evalModel) {
        List<ModelParam> modelParams = modelParamsMapper.selectList(new QueryWrapper<ModelParam>().eq("eval_model_id", evalModel.getId()));
        if (modelParams != null) {
            modelParamsMapper.delete(new QueryWrapper<ModelParam>().eq("eval_model_id", evalModel.getId()));
        }
        evalModelMapper.deleteById(evalModel);
    }

    @Override
    public List<ListModelResp> getList(Page<ListModelResp> page, ListModelReq req) {
        List<ListModelResp> list = evalModelMapper.getList(page, req);
        if (list != null) {
            for (ListModelResp evalModel : list) {
                List<ModelParam> modelParams = modelParamsMapper.selectList(new QueryWrapper<ModelParam>().eq("eval_model_id", evalModel.getId()));
                evalModel.setParams("");
                if (modelParams != null) {
                    for (ModelParam modelParam : modelParams)
                        evalModel.setParams(evalModel.getParams()  + modelParam.getName()+",");
                }
            }

        }
        return list;
    }
}
