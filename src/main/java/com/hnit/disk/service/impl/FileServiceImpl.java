package com.hnit.disk.service.impl;

import com.hnit.disk.hadoop.HdfsClient;
import com.hnit.disk.response.ResMsg;
import com.hnit.disk.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author: liguangming
 * @Date: 2020/8/24
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {
    @Value("${file.upload}")
    private String fileLoadPath;

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

}
