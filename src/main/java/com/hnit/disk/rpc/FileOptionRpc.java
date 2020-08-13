package com.hnit.disk.rpc;

import com.hnit.disk.response.ResMsg;
import com.hnit.disk.rpc.api.FileOptionApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j(topic = "[文件操作RPC服务]")
@Service
public class FileOptionRpc implements FileOptionApi {

    @Override
    public ResMsg<Boolean> addFile(String path, String filename) {
        return null;
    }

    @Override
    public ResMsg<Boolean> deleteFile(String path, String filename) {
        return null;
    }
    @Override
    public ResMsg<Boolean> renameFile(String path, String filename) {
        return null;
    }
}
