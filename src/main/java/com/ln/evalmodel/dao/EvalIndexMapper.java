package com.ln.evalmodel.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ln.evalmodel.model.EvalIndex;
import com.ln.evalmodel.model.vo.ListEvalIndexReq;
import com.ln.evalmodel.model.vo.ListEvalIndexResp;
import com.ln.evalmodel.node.ZTreeNode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author liuN
 * @Date 2020/10/14 0014 23:27
 * @Version 1.0
 */
@Repository
public interface EvalIndexMapper extends BaseMapper<EvalIndex> {
    List<ListEvalIndexResp> getList(@Param("page") Page<ListEvalIndexResp> page, @Param("req") ListEvalIndexReq req);

    List<ZTreeNode> indexTreeList(@Param("req") ListEvalIndexReq req);
}
