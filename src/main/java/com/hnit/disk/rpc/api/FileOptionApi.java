package com.hnit.disk.rpc.api;
import com.hnit.disk.response.ResMsg;

/**
 * 文件操作接口
 */
public interface FileOptionApi {
    /**
     * 增加目录
     * @return
     */
    public ResMsg<Boolean> addCatalog(String path, String catalogName);

    /**
     * 删除目录
     */
    public ResMsg<Boolean> deleteCatalog(String path,String catalogName);

    /**
     * 目录重命名
     */
    public ResMsg<Boolean> renameCatalog(String path,String catalogName);

    /**
     * 添加文件
     */
    public ResMsg<Boolean> addFile(String path,String filename);

    /**
     * 删除文件
     */
    public ResMsg<Boolean> deleteFile(String path,String filename);

    /**
     * 文件重命名
     */
    public ResMsg<Boolean> renameFile(String path,String filename);


}
