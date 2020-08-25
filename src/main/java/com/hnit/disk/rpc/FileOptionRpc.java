package com.hnit.disk.rpc;

import com.hnit.disk.response.FileNodeVO;
import com.hnit.disk.response.ResMsg;
import com.hnit.disk.service.CatalogService;
import com.hnit.disk.service.FileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/disk/fileCenter/fileOption")
public class FileOptionRpc {
    @Value("${file.upload}")
    private String fileLoadPath;

    @Autowired
    private FileService fileService;

    @Autowired
    private CatalogService catalogService;

    /**
     * 添加文件
     */
    @ApiOperation(value = "添加文件",notes = "添加文件")
    @PostMapping(value = "/uploadFile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResMsg<Boolean> uploadFile(@RequestPart MultipartFile file, @RequestParam("toPath") String toPath) throws IOException, ServletException {
        ResMsg<Boolean> booleanResMsg = fileService.uploadFile(file, toPath);
        return booleanResMsg;
    }
    /**
     * 删除文件
     */
    @ApiOperation(value = "删除节点",notes = "删除节点")
    @RequestMapping("/deleteNode")
    public ResMsg<Boolean> deleteFile(String path, String name) {
        ResMsg resMsg = fileService.deleteNode(path, name);
        return resMsg;
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
    @ApiOperation(value = "文件下载",notes = "文件下载")
    @RequestMapping("/download")
    public ResMsg<File> download(String filePath,String fileName){
        File file = new File(fileLoadPath + File.separatorChar + fileName);
        if(file.exists()){
            return ResMsg.builderSuccess(file);
        }
        Boolean result  = fileService.download(filePath,fileName);
        if(result){
            return ResMsg.builderSuccess(new File(fileLoadPath + File.separatorChar + fileName));
        }
        return ResMsg.builderFail();
    }
    /**
     * 文件分类
     */
    @ApiOperation(value = "文件分类",notes = "文件分类")
    @RequestMapping("/orderBy")
    public ResMsg<List<FileNodeVO>> orderBy(@RequestParam("path")String path,@RequestParam("type") String type){
        if(type.equals("all")){
            try {
                List<FileNodeVO> files = catalogService.getFiles(path);
                return ResMsg.builderSuccess(files);
            } catch (IOException e) {
                return ResMsg.builderFail("网络异常");
            }
        }
        List<FileNodeVO> fileNodeVOS = fileService.orderBy(path, type);
        return ResMsg.builderSuccess(fileNodeVOS);
    }
}
