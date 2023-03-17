//package com.randing;
//
//
//import com.randing.common.exception.BaseException;
//import com.randing.common.utils.uuid.UUID;
//import com.randing.system.domain.po.Unit;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * <p>
// * 前端控制器
// * </p>
// *
// * @author Leen
// * @since 2023-03-15
// */
//@RestController
//@RequestMapping("/unit")
//public class UnitController {
//
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
//        String contactsPhone = unit.getContactsPhone();//联系方式
//        if ((StringUtils.isBlank(contactsPhone))) {
//            throw new BaseException("联系方式 不能为空");
//        }
//    }
//
//}
//
