package com.ln.evalmodel.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ln.evalmodel.model.Evaluation;
import com.ln.evalmodel.model.vo.ListEvaluationReq;
import com.ln.evalmodel.model.vo.ListEvaluationResp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author liuN
 * @Date 2020/10/25 0025 3:26
 * @Version 1.0
 */
@Repository
public interface EvaluationMapper extends BaseMapper<Evaluation> {
    List<ListEvaluationResp> getList(@Param("page") Page<ListEvaluationResp> page,@Param("req") ListEvaluationReq req);
}
