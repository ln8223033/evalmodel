package com.ln.evalmodel.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ln.evalmodel.model.EvalModel;
import com.ln.evalmodel.model.vo.AddEvalModelVo;
import com.ln.evalmodel.model.vo.ListModelReq;
import com.ln.evalmodel.model.vo.ListModelResp;

import java.util.List;

/**
 * @Author liuN
 * @Date 2020/10/6 0006 15:51
 * @Version 1.0
 */
public interface IEvalModelService extends IService<EvalModel> {

    void saveEvalModel(AddEvalModelVo evalModelVo);

    void editEvalModel(AddEvalModelVo evalModelVo);

    void removeEvalModel(EvalModel evalModel);

    List<ListModelResp> getList(Page<ListModelResp> page, ListModelReq req);
}
