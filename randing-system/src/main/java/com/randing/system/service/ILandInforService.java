package com.randing.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.system.domain.po.LandInfor;
import com.baomidou.mybatisplus.extension.service.IService;
import com.randing.system.domain.vo.LandInforVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Leen
 * @since 2022-12-15
 */
public interface ILandInforService extends IService<LandInfor> {

    Page<LandInfor> listPage(LandInforVo landInforVo);

    LandInforVo findById(Long id);

    List<String> landAscriptionList();
}
