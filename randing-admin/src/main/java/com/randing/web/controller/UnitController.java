package com.randing;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.randing.common.core.domain.AjaxResult;
import com.randing.common.exception.BaseException;
import com.randing.system.domain.po.Unit;
import com.randing.system.service.IUnitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Leen
 * @since 2023-03-15
 */
@RestController
@Api(value = "单位信息")
public class UnitController {
    @Autowired
    private IUnitService unitService;
//    @PostMapping("add")
//    public void insert(@RequestBody Unit unit) {
//
//        if (unit == null) {
//            throw new BaseException("参数错误");
//        }
//        String unitName = unit.getUnitName();//单位名称
//        if (StringUtils.isBlank(unitName)) {
//            throw new BaseException("单位/个人单位名称 不能为空");
//        }
//        String unitAddress = unit.getUnitAddress();//南繁地址
//        if (StringUtils.isBlank(unitAddress)) {
//            throw new BaseException("南繁地址 不能为空");
//        }
//        String contactsPhone = unit.getContactsPhone();//联系方式
//        if ((StringUtils.isBlank(contactsPhone))) {
//            throw new BaseException("联系方式 不能为空");
//        }
//
////        String contactsPhone = unit.getContactsPhone();//联系方式
////        if ((StringUtils.isBlank(contactsPhone))) {
////            throw new BaseException("联系方式 不能为空");
////        }
//    }
    @ApiOperation("单位列表 模糊查询按照单位名称 20230709")
    @RequestMapping(value = "/unit/findByUnitName", method = RequestMethod.POST)
    public AjaxResult<List<Unit>> findByUnitName(@RequestBody Unit unit) {
        if (unit == null) {
            throw new BaseException("参数错误");
        }
        List<Unit> list = unitService.findByLikeUnitName(unit);
        return AjaxResult.success(list);
    }
    @ApiOperation("单位列表 查询按照企业代码creditCode 20230709")
    @RequestMapping(value = "/unit/getUnitByCreditCode", method = RequestMethod.POST)
    public AjaxResult<Unit> getUnitByCreditCode(@RequestBody Unit unit) {
        if (unit == null || StringUtils.isEmpty(unit.getCreditCode())) {
            throw new BaseException("参数错误");
        }
        Unit one = unitService.getOne(Wrappers.lambdaQuery(Unit.class).eq(Unit::getCreditCode, unit.getCreditCode()));
        if (one == null) {
            throw new BaseException("未检测到此信用代码的单位信息");
        }
        return AjaxResult.success(one);
    }

}

