package com.hnit.disk.rpc;

import com.hnit.disk.response.ResMsg;
import com.hnit.disk.rpc.api.CatalogOptionApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: liguangming
 * @Date: 2020/8/13
 */
@Slf4j(topic = "[目录操作RPC服务]")
@Service
public class CatalogOptionRpc implements CatalogOptionApi {
    @Override
    public ResMsg<Boolean> addCatalog(@RequestBody String path, String catalogName) {
        return null;
    }

    @Override
    public ResMsg<Boolean> deleteCatalog(@RequestBody String path, String catalogName) {
        return null;
    }

    @Override
    public ResMsg<Boolean> renameCatalog(@RequestBody String path, String catalogName) {
        return null;
    }
}
