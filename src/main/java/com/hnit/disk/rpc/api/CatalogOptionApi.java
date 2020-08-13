package com.hnit.disk.rpc.api;

import com.hnit.disk.response.ResMsg;

/**
 * @Author: liguangming
 * @Date: 2020/8/13
 */

public interface CatalogOptionApi {
    /**
     * 增加目录
     * @return
     */
    default ResMsg<Boolean> addCatalog(String path, String catalogName) {
        return ResMsg.builderNotImplResp();
    }
    /**
     * 删除目录
     */
    default ResMsg<Boolean> deleteCatalog(String path,String catalogName){
        return ResMsg.builderNotImplResp();
    }

    /**
     * 目录重命名
     */
    default ResMsg<Boolean> renameCatalog(String path,String catalogName){
        return ResMsg.builderNotImplResp();
    }
}
