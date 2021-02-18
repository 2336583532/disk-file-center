package com.hnit.disk.service.impl;

import com.hnit.disk.em.LenEnum;
import com.hnit.disk.hadoop.HdfsClient;
import com.hnit.disk.response.FileNodeVO;
import com.hnit.disk.response.ResMsg;
import com.hnit.disk.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.hdfs.protocol.SnapshotDiffReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: liguangming
 * @Date: 2020/8/24
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {
    @Value("${file.upload}")
    private String fileLoadPath;
    @Value("${file.type.pic}")
    private String PIC;
    @Value("${file.type.video}")
    private String VIDEO;
    @Value("${file.type.doc}")
    private String DOC;
    @Value("${file.type.music}")
    private String MUSIC;



    @Autowired
    private HdfsClient hdfsClient;

    @Override
    public ResMsg<Boolean> uploadFile(MultipartFile multipartFile,String toPath) {
        if (multipartFile == null || multipartFile.getSize() < 1) {
            return ResMsg.builderFail("文件为空");
        }
        File file = createFilePathIfNotExist();
        String fileName = file.getAbsolutePath() + File.separatorChar + multipartFile.getOriginalFilename();
        try {
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(fileName);
            Files.write(path, bytes);
            Boolean result = hdfsClient.uploadFile(fileName, toPath);
            return ResMsg.builderSuccess(result);
        } catch (IOException e) {
            log.error("FileServiceImpl uploadFile,%s",e);
            return ResMsg.builderFail("添加失败");
        }

    }

    @Override
    public ResMsg deleteNode(String path, String name) {
        if(StringUtils.isEmpty(path) && StringUtils.isEmpty(name)){
            return ResMsg.builderFail("文件不存在");
        }
        Boolean result = hdfsClient.deleteNode(path+name);
        return ResMsg.builderSuccess(result);
    }

    @Override
    public Boolean download(String filePath,String fileName) {
        createFilePathIfNotExist();
        hdfsClient.download(filePath+fileName,fileLoadPath);
        return true;
    }

    @Override
    public List<FileNodeVO> orderBy(String path, String type) {
        RemoteIterator<LocatedFileStatus> files = hdfsClient.getFiles(path);
        List<FileNodeVO> fileList = new ArrayList<FileNodeVO>();
        while (true) {
            try {
                if (!files.hasNext()) {
                    break;
                }
                LocatedFileStatus next = files.next();
                if(next.isFile() && isType(next,type)){
                    FileNodeVO fileNode = FileNodeVO.builder().fileName(next.getPath().getName()).updateTime( new Date(next.getModificationTime())).isFolder(next.isDirectory()).build();
                    long len = next.getLen();
                    if(len < LenEnum.B.getLen()){
                        fileNode.setFileSize(len+"B");
                    }else if(len >= LenEnum.B.getLen() && len < LenEnum.KB.getLen()) {
                        fileNode.setFileSize(len*1.0/LenEnum.B.getLen()+"KB");
                    }else if(len >= LenEnum.KB.getLen() && len < LenEnum.MB.getLen()){
                        fileNode.setFileSize(len*1.0/LenEnum.KB.getLen()+"MB");
                    }else {
                        fileNode.setFileSize(len*1.0/LenEnum.MB.getLen()+"GB");
                    }
                    fileList.add(fileNode);
                }
            } catch (IOException e) {
                log.error("file-center:getFile:%s", e);
            }

        }
        return fileList;
    }

    /**
     * 创建临时路径
     *
     * @return
     */
    private File createFilePathIfNotExist() {
        // 新建文件
        File dir = new File(fileLoadPath);
        boolean exists = dir.exists();
        if (!exists) {
            log.info("文件目录不存在，自动创建.......");
            dir.mkdirs();
        }
        return dir;
    }


    private Boolean isType(LocatedFileStatus next,String type){
        if(type.equals("pic")){
            return isThisType(PIC,next.getPath().getName());
        }else if(type.equals("video")){
            return isThisType(VIDEO,next.getPath().getName());
        }else if(type.equals("doc")){
            return isThisType(DOC,next.getPath().getName());
        }else if(type.equals("music")){
            return isThisType(MUSIC,next.getPath().getName());
        }else {
            return isThisType("other",next.getPath().getName());
        }
    }

    private Boolean isThisType(String typeStr,String name){
        if(typeStr.equals("other")){
            typeStr = PIC+","+VIDEO+","+DOC+","+MUSIC;
            String[] split = typeStr.split(",");
            for (String str:split){
                if(name.endsWith(str)){
                    return true;
                }
            }
            return false;
        }
        String[] split = typeStr.split(",");
        for (String str:split){
            if(name.endsWith(str)){
                return true;
            }
        }
        return false;
    }
}
