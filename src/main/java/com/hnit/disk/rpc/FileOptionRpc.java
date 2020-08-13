package com.hnit.disk.rpc;

import com.hnit.disk.response.ResMsg;
import com.hnit.disk.rpc.api.FileOptionApi;

public class FileOptionRpc implements FileOptionApi {
    @Override
    public ResMsg<Boolean> addCatalog(String path, String catalogName) {
        return null;
    }

    @Override
    public ResMsg<Boolean> deleteCatalog(String path, String catalogName) {
        return null;
    }

    @Override
    public ResMsg<Boolean> renameCatalog(String path, String catalogName) {
        return null;
    }

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
