package com.hnit.disk.rpc;

import com.hnit.disk.response.FileNodeVO;
import com.hnit.disk.response.ResMsg;
import com.hnit.disk.service.CatalogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @Author: liguangming
 * @Date: 2020/8/13
 */
@RestController
@RequestMapping("/disk/fileCenter/catelogOption")
public class CatalogOptionRpc {
    @Autowired
    private CatalogService catalogService;

    @ApiOperation(value = "获取全部文件",notes = "获取全部文件")
    @GetMapping("/getFiles")
    public ResMsg<List<FileNodeVO>> getFiles( String path) {
        try {
            List<FileNodeVO> files = catalogService.getFiles(path);
            return ResMsg.builderSuccess(files);
        } catch (IOException e) {
            return ResMsg.builderFail();
        }
    }

    @PostMapping("/mkdir")
    @ApiOperation(value = "增加目录",notes = "增加目录")
    public ResMsg<Boolean> mkdir(@RequestBody String path, String catalogName) {
        Boolean result = null;
        if(path.equals("/")){
           result = catalogService.mkdir(path+catalogName);
        }else {
           result = catalogService.mkdir(path+"/"+catalogName);
        }
        if (result){
            return ResMsg.builderSuccess(result);
        }else{
            return ResMsg.builderFail();
        }
    }


    @GetMapping("/rename")
    @ApiOperation(value = "目录重命名",notes = "目录重命名")
    public ResMsg<Boolean> renameDir(String path, String newName,String oldName) {
        Boolean rename = catalogService.rename(path, oldName, newName);
        return ResMsg.builderSuccess(rename);
    }
}
