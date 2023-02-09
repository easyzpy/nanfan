package com.randing.web.controller;


import com.randing.common.core.domain.AjaxResult;
import com.randing.system.domain.vo.ApplyBatchVo;
import com.randing.system.domain.vo.FavoriteReqDTO;
import com.randing.system.service.IApplyBatchService;
import com.randing.system.service.ILandFavoritesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Leen
 * @since 2022-12-21
 */
@RestController
@Api(value = "收藏")
@RequestMapping("/favorites")
public class LandFavoritesController {

    @Autowired
    private ILandFavoritesService landFavoritesService;
    @PostMapping("addFavoriteLand")
    @ApiOperation("加入收藏")
    public AjaxResult addFavoriteLand(@RequestBody FavoriteReqDTO favoriteReqDTO) {
        int i = landFavoritesService.addFavoriteLand(favoriteReqDTO);
        if (i == 0) {
            return AjaxResult.success("收藏成功");
        }
        return AjaxResult.error("收藏失败");
    }

    @PutMapping("removeFavoriteLand")
    @ApiOperation("加入收藏")
    public AjaxResult removeFavoriteLand(@RequestBody FavoriteReqDTO favoriteReqDTO) {
        int i = landFavoritesService.removeFavoriteLand(favoriteReqDTO);
        if (i == 0) {
            return AjaxResult.error("取消收藏失败");
        }
        return AjaxResult.success("取消收藏成功");
    }


}

