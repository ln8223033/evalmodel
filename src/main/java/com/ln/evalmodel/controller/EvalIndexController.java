package com.ln.evalmodel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ln.evalmodel.common.Result;
import com.ln.evalmodel.common.ResultEnum;
import com.ln.evalmodel.common.factory.PageFactory;
import com.ln.evalmodel.common.groups.New;
import com.ln.evalmodel.model.EvalIndex;
import com.ln.evalmodel.model.JudgmentMatrix;
import com.ln.evalmodel.model.vo.ListEvalIndexReq;
import com.ln.evalmodel.model.vo.ListEvalIndexResp;
import com.ln.evalmodel.node.ZTreeNode;
import com.ln.evalmodel.service.IEvalIndexService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author liuN
 * @Date 2020/10/9 0014 23:32
 * @Version 1.0
 */
@RestController
@RequestMapping({"evalIndex"})
@Api(value = "评价指标", tags = "评价指标接口")
public class EvalIndexController {
    @Autowired
    private IEvalIndexService evalIndexService;

    /**
     * 评价指标列表
     */
    @ApiOperation("评价指标分页列表")
    @GetMapping("/list")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(value = "模型id", name = "evalModelId", required = true),
    })
    public Result getList(ListEvalIndexReq req) {
        Result result = new Result(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());

        try {
            Page<ListEvalIndexResp> page = new PageFactory<ListEvalIndexResp>().defaultPage();
            List<ListEvalIndexResp> list = evalIndexService.getList(page, req);
            page.setRecords(list);
            result.setData(page);
        } catch (Exception e) {
            result.setCode(ResultEnum.ERROR.getCode());
            result.setMsg(ResultEnum.ERROR.getMsg());
        }
        return result;
    }

    /**
     * 评价指标列表
     */
    @ApiOperation("评价指标列表(选择父级指标用)")
    @GetMapping(value = "/selectIndexTreeList")
    @ResponseBody
    public List<ZTreeNode> selectIndexTreeList(ListEvalIndexReq req) {
        List<ZTreeNode> indexTreeList = evalIndexService.indexTreeList(req);
        indexTreeList.add(ZTreeNode.createParent());
        return indexTreeList;
    }

    /**
     * 新增
     */
    @ApiOperation("新增")
    @PostMapping("")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(value = "模型id", name = "evalModelId", required = true),
            @ApiImplicitParam(value = "指标名称", name = "name", required = true),
            @ApiImplicitParam(value = "父指标id,选择顶级传0", name = "parentId", required = true),
            @ApiImplicitParam(value = "指标排序从0开始", name = "num", required = true),
            @ApiImplicitParam(value = "url地址", name = "url", required = false),
    })
    public Result save(@Validated({New.class}) EvalIndex evalIndex) {
        Result result = new Result(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), "");
        try {
            evalIndexService.saveEvalIndex(evalIndex);
        } catch (Exception e) {
            result.setCode(ResultEnum.ERROR.getCode());
            result.setMsg(ResultEnum.ERROR.getMsg());
        }

        return result.ok("提交数据成功");
    }

    /**
     * 构建判断矩阵
     */
    @ApiOperation("构建判断矩阵")
    @PostMapping("judgmentMatrix")
    public Result saveJudgmentMatrix(@ApiParam(value = "父指标id不能为空", name = "parentEvalIndexId", required = true) @RequestParam Long parentEvalIndexId,
                                     @ApiParam(value = "模型id不能为空", name = "evalModelId", required = true) @RequestParam Long evalModelId) {
        Result result = new Result(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), "");
        List<EvalIndex> evalIndexList = evalIndexService.list(new QueryWrapper<EvalIndex>().eq("parent_id", parentEvalIndexId).eq("eval_model_id",evalModelId).groupBy("parent_id","num"));
        try {
            if (evalIndexList.size() == 0) {
                result.setCode(ResultEnum.ERROR.getCode());
                result.setMsg("该指标没有子指标无法构建判断矩阵");
            } else {
              List<JudgmentMatrix> judgmentMatrixList =  evalIndexService.saveJudgmentMatrix(parentEvalIndexId,evalModelId,evalIndexList);
                result.setData(judgmentMatrixList);
            }
        } catch (Exception e) {
            result.setCode(ResultEnum.ERROR.getCode());
            result.setMsg(ResultEnum.ERROR.getMsg());
        }
        return result;
    }

    /**
     * 判断矩阵权值计算
     */
    @ApiOperation("判断矩阵权值计算")
    @PostMapping("judgmentMatrix/calculate")
    public Result calculate(@ApiParam(value = "父指标id不能为空", name = "parentEvalIndexId", required = true) @RequestParam Long parentEvalIndexId,
                            @ApiParam(value = "模型id不能为空", name = "evalModelId", required = true) @RequestParam Long evalModelId,
                            @RequestBody List<JudgmentMatrix> judgmentMatrixList) {
        Result result = new Result(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), "");

        Map<String, Integer> map = evalIndexService.calculate(parentEvalIndexId,evalModelId,judgmentMatrixList);
        if (map.get("result")==0){
            result.setCode(ResultEnum.ERROR.getCode());
            result.setMsg("一致性验证失败");
            result.setData(map);
        }

        return result;
    }
}
