package com.hnit.disk.rpc.api;
import com.hnit.disk.response.ResMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 文件操作接口
 */
@Api("文件操作接口文档")
@RequestMapping(value = "/api/fileCenter/fileOptin")
public interface FileOptionApi {

    /**
     * 添加文件
     */
    @ApiOperation(value = "添加文件",notes = "添加文件")
    @RequestMapping("/addFile")
    default ResMsg<Boolean> addFile(@RequestBody String path, String filename){
        return ResMsg.builderNotImplResp();
    }

    /**
     * 删除文件
     */
    @ApiOperation(value = "删除文件",notes = "删除文件")
    @RequestMapping("/deleteFile")
    default ResMsg<Boolean> deleteFile(@RequestBody String path,String filename){
        return ResMsg.builderNotImplResp();
    }

    /**
     * 文件重命名
     */
    @ApiOperation(value = "文件重命名",notes = "文件重命名")
    @RequestMapping("/renameFile")
    default ResMsg<Boolean> renameFile(@RequestBody String path,String filename){
        return ResMsg.builderNotImplResp();
    }


}
