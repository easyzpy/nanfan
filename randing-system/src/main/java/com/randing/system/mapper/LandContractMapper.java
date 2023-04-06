package com.randing.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.system.domain.po.LandContract;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.randing.system.domain.vo.LandContractDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Leen
 * @since 2023-01-15
 */
public interface LandContractMapper extends BaseMapper<LandContract> {
/* @TableField(exist = false)
    private String LandName;
    @TableField(exist = false)
    private Long landId;
    @TableField(exist = false)
    private String landLocation;*/
    @Select("SELECT *" +
            ", d.land_name" +
            ",d.id land_id" +
            ",d.land_location land_location " +
            "FROM land_contract as c " +
            "LEFT JOIN land_infor as d on c.choice_land_id = d.id " +
            "${ew.customSqlSegment}")
    public Page<LandContractDTO> getList(IPage page, @Param(Constants.WRAPPER) Wrapper wrapper);

}
