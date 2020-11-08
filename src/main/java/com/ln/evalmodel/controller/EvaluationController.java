package com.ln.evalmodel.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ln.evalmodel.common.Result;
import com.ln.evalmodel.common.ResultEnum;
import com.ln.evalmodel.common.factory.PageFactory;
import com.ln.evalmodel.common.groups.New;
import com.ln.evalmodel.model.IndexScore;
import com.ln.evalmodel.model.vo.AddEvaluationVo;
import com.ln.evalmodel.model.vo.EvaluationDetailVo;
import com.ln.evalmodel.model.vo.ListEvaluationReq;
import com.ln.evalmodel.model.vo.ListEvaluationResp;
import com.ln.evalmodel.service.IEvaluationService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author liuN
 * @Date 2020/10/25 0025 3:33
 * @Version 1.0
 */
@RestController
@RequestMapping({"evaluation"})
@Api(value = "评价", tags = "评价接口")
public class EvaluationController {
    @Autowired
    private IEvaluationService evaluationService;

    /**
     * 新增
     */
    @ApiOperation("新增")
    @PostMapping("")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(value = "模型id", name = "evalModelId", required = true),
            @ApiImplicitParam(value = "模型参数", name = "params", required = true, paramType = "数组", dataType = "object"),
    })
    public Result save(@Validated({New.class}) AddEvaluationVo evaluationVo) {
        Result result = new Result(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), "");
        try {
            evaluationService.saveEvaluation(evaluationVo);
        } catch (Exception e) {
            result.setCode(ResultEnum.ERROR.getCode());
            result.setMsg(ResultEnum.ERROR.getMsg());
        }
        return result.ok("提交数据成功");
    }

    /**
     * 评价列表
     */
    @ApiOperation("评价分页列表")
    @GetMapping("/list")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(value = "模型id", name = "evalModelId", required = false),
    })
    public Result getList(ListEvaluationReq req) {
        Result result = new Result(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
        try {
            Page<ListEvaluationResp> page = new PageFactory<ListEvaluationResp>().defaultPage();
            List<ListEvaluationResp> list = evaluationService.getList(page, req);
            page.setRecords(list);
            result.setData(page);
        } catch (Exception e) {
            result.setCode(ResultEnum.ERROR.getCode());
            result.setMsg(ResultEnum.ERROR.getMsg());
        }
        return result;
    }

    /**
     * 评价详情
     */
    @ApiOperation("评价详情")
    @GetMapping("/detail")
    public Result getDetail(@ApiParam(value = "id", name = "id", required = true) @RequestParam("id") Long id) {
        Result result = new Result(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
        try {
            EvaluationDetailVo evaluationDetail = evaluationService.getDetail(id);
            result.setData(evaluationDetail);
        } catch (Exception e) {
            result.setCode(ResultEnum.ERROR.getCode());
            result.setMsg(ResultEnum.ERROR.getMsg());
        }
        return result;
    }
    /**
     * 计算评价结果
     */
    @ApiOperation("计算评价结果")
    @PostMapping("/calculate")
    public Result calculate(@RequestBody List<IndexScore> indexScores) {
        Result result = new Result(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
        try {
            EvaluationDetailVo evaluationDetail = evaluationService.calculate(indexScores);
            result.setData(evaluationDetail);
        } catch (Exception e) {
            result.setCode(ResultEnum.ERROR.getCode());
            result.setMsg(ResultEnum.ERROR.getMsg());
        }
        return result;
    }
}
