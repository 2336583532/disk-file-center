package com.hnit.disk.rpc.api;

import com.hnit.disk.response.ResMsg;
import com.hnit.disk.rpc.CatalogOptionRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: liguangming
 * @Date: 2020/8/13
 */
@Api("目录操作接口文档")
@FeignClient(value = "file-center", fallback = CatalogOptionRpc.class)
@RequestMapping(value = "/api/fileCenter/catalogOption")
public interface CatalogOptionApi {
    /**
     * 增加目录
     * @return
     */
    @ApiOperation(value = "增加目录",notes = "增加目录")
    default ResMsg<Boolean> mkdir(String path, String catalogName) {
        return ResMsg.builderNotImplResp();
    }
    /**
     * 删除目录
     */
    @ApiOperation(value = "删除目录",notes = "删除目录")
    default ResMsg<Boolean> deleteDir(String path,String catalogName){
        return ResMsg.builderNotImplResp();
    }
    /**
     * 目录重命名
     */
    @ApiOperation(value = "目录重命名",notes = "目录重命名")
    default ResMsg<Boolean> renameDir(String path,String catalogName){
        return ResMsg.builderNotImplResp();
    }
}
