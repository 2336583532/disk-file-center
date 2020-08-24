package com.hnit.disk.service;

import com.hnit.disk.response.ResMsg;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @Author: liguangming
 * @Date: 2020/8/24
 */
@Service
public interface FileService {
    public ResMsg<Boolean> uploadFile(MultipartFile file,String toPath);

    public ResMsg deleteNode(String path, String name);
}