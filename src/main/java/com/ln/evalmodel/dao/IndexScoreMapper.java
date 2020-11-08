package com.ln.evalmodel.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ln.evalmodel.model.IndexScore;
import com.ln.evalmodel.model.vo.IndexScoreDetailVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author liuN
 * @Date 2020/10/26 0026 1:13
 * @Version 1.0
 */
@Repository
public interface IndexScoreMapper extends BaseMapper<IndexScore> {
    List<IndexScoreDetailVo> getIndexScoreDetailList(@Param("evaluationId") Long id,@Param("evalModelId") Long evalModelId,@Param("parentId") Long parentId);
}
