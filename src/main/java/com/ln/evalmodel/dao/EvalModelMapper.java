package com.ln.evalmodel.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ln.evalmodel.model.EvalModel;
import com.ln.evalmodel.model.vo.ListModelReq;
import com.ln.evalmodel.model.vo.ListModelResp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author liuN
 * @Date 2020/10/6 0006 15:48
 * @Version 1.0
 */
@Repository
public interface EvalModelMapper extends BaseMapper<EvalModel> {
    List<ListModelResp> getList(@Param("page") Page<ListModelResp> page, @Param("req") ListModelReq req);
}
