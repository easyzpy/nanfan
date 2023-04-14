package com.randing.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.system.domain.po.LandRetreat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.randing.system.domain.vo.LandContractDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Leen
 * @since 2023-01-29
 */
public interface LandRetreatMapper extends BaseMapper<LandRetreat> {



    @Select("select e.land_area_total, e.land_area_surplus, e.land_area_usable,f.land_apply_area, e.land_name, c.* " +
            "FROM land_retreat as c " +
            "LEFT JOIN land_contract as d on c.contract_id = d.contract_id " +
            "LEFT JOIN land_infor as e on d.choice_land_id = e.id  " +
            "LEFT JOIN nanfan_land_apply_form as f on f.id = d.apply_id  " +
            "${ew.customSqlSegment}")
    public Page<LandRetreat> selectCustomPage(IPage page, @Param(Constants.WRAPPER) Wrapper wrapper);
}
