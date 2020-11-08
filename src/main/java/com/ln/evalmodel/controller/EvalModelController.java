package com.ln.evalmodel.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ln.evalmodel.common.Result;
import com.ln.evalmodel.common.ResultEnum;
import com.ln.evalmodel.common.factory.PageFactory;
import com.ln.evalmodel.common.groups.New;
import com.ln.evalmodel.model.EvalModel;
import com.ln.evalmodel.model.vo.AddEvalModelVo;
import com.ln.evalmodel.model.vo.ListModelReq;
import com.ln.evalmodel.model.vo.ListModelResp;
import com.ln.evalmodel.service.IEvalModelService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Author liuN
 * @Date 2020/10/6 0006 15:55
 * @Version 1.0
 */
@RestController
@RequestMapping({"evalModel"})
@Api(value = "评价模型", tags = "评价模型接口")
public class EvalModelController {
    @Autowired
    private IEvalModelService evalModelService;
    /**
     * 新增
     */
    @ApiOperation("新增")
    @PostMapping("")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(value = "模型名称", name = "name", required = true),
            @ApiImplicitParam(value = "模型参数", name = "modelParams", required = true, paramType = "数组", dataType = "object"),
    })
    public Result save(@Validated({New.class})AddEvalModelVo evalModelVo) {
        Result result = new Result(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), "");
        try {
            evalModelService.saveEvalModel(evalModelVo);
        }catch (Exception e){
            result.setCode(ResultEnum.ERROR.getCode());
            result.setMsg(ResultEnum.ERROR.getMsg());
        }

        return result.ok("提交数据成功");
    }
    /**
     * 编辑
     */
    @ApiOperation("编辑")
    @PutMapping("")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(value = "id", name = "id", required = true),
            @ApiImplicitParam(value = "模型名称", name = "name", required = true),
            @ApiImplicitParam(value = "模型参数", name = "modelParams", required = true, paramType = "数组", dataType = "object"),
    })
    public Result edit(@Validated({New.class})AddEvalModelVo evalModelVo) {
        Result result = new Result(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), "");
        try {
                EvalModel evalModel = evalModelService.getById(evalModelVo.getId());
                if (evalModel == null){
                    result.setCode(ResultEnum.ERROR.getCode());
                    result.setMsg("数据异常，未查询到模型信息！");
                    return result;
            }
            evalModelService.editEvalModel(evalModelVo);
        }catch (Exception e){
            result.setCode(ResultEnum.ERROR.getCode());
            result.setMsg(ResultEnum.ERROR.getMsg());
        }
        return result.ok("修改数据成功");
    }
    /**
     * 删除
     */
    @ApiOperation("删除")
    @DeleteMapping("{id}")
    public Result remove(@ApiParam(value = "id", name = "id", required = true) @PathVariable("id") Integer id) {
        Result result = new Result(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), "");
        try {
            EvalModel evalModel = evalModelService.getById(id);
            if (evalModel == null){
                result.setCode(ResultEnum.ERROR.getCode());
                result.setMsg("数据异常，未查询到模型信息！");
                return result;
            }
            evalModelService.removeEvalModel(evalModel);
        }catch (Exception e){
            result.setCode(ResultEnum.ERROR.getCode());
            result.setMsg(ResultEnum.ERROR.getMsg());
        }
        return result.ok("删除数据成功");
    }
    /**
     * 模型列表
     */
    @ApiOperation("模型分页列表")
    @GetMapping("/list")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(value = "模型名称", name = "name", required = false),
    })
    public Result getList(ListModelReq req) {
        Result result = new Result(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
        try {
            Page<ListModelResp> page = new PageFactory<ListModelResp>().defaultPage();
            List<ListModelResp> list = evalModelService.getList(page, req);
            page.setRecords(list);
            result.setData(page);
        } catch (Exception e) {
            result.setCode(ResultEnum.ERROR.getCode());
            result.setMsg(ResultEnum.ERROR.getMsg());
        }
        return result;
    }
}
