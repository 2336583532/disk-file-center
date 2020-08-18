package com.hnit.disk.rpc;

import com.hnit.disk.response.FileNodeVO;
import com.hnit.disk.response.ResMsg;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController("/disk/fileCenter/fileOption")
public class FileOptionRpc {
    /**
     * 添加文件
     */
    @ApiOperation(value = "添加文件",notes = "添加文件")
    @PostMapping("/uploadFile")
    public ResMsg<Boolean> uploadFile(@RequestBody MultipartFile file) {
        return null;
    }
    /**
     * 删除文件
     */
    @ApiOperation(value = "删除文件",notes = "删除文件")
    @RequestMapping("/deleteFile")
    public ResMsg<Boolean> deleteFile(String path, String filename) {
        return null;
    }

    /**
     * 文件重命名
     */
    @ApiOperation(value = "文件重命名",notes = "文件重命名")
    @RequestMapping("/renameFile")
    public ResMsg<Boolean> renameFile(String path, String filename) {
        return null;
    }

    /**
     * 获得路径下所有文件夹和文件
     */
    @ApiOperation(value = "获得路径下所有文件夹和文件",notes = "获得路径下所有文件夹和文件")
    @RequestMapping("/getFileByPath")
    public ResMsg<FileNodeVO> getFileByPath(String path){
        return null;
    }
}
