package com.hnit.disk.service.impl;
import com.hnit.disk.em.LenEnum;
import com.hnit.disk.hadoop.HdfsClient;
import com.hnit.disk.response.FileNodeVO;
import com.hnit.disk.service.CatalogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.RemoteIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: liguangming
 * @Date: 2020/8/17
 */
@Service
@Slf4j
public class CatalogServiceImpl implements CatalogService {
    @Autowired
    private HdfsClient hdfsClient;


    @Override
    public Boolean isDir(String path) {
        return null;
    }

    @Override
    public Boolean dirExist(String path) {
        return null;
    }

    @Override
    public Boolean mkdir(String path) {
        int index = path.lastIndexOf("/");
        String parentPath = path.substring(0, index);
        if (((hdfsClient.isDir(parentPath) && hdfsClient.exist(parentPath)) || index == 0) && !hdfsClient.exist(path)) {
            return hdfsClient.mkdir(path);
        }
        return false;
    }

    @Override
    public List<FileNodeVO> getFiles(String path) {
        RemoteIterator<LocatedFileStatus> files = hdfsClient.getFiles(path);
        List<FileNodeVO> fileList = new ArrayList<FileNodeVO>();
        while (true) {
            try {
                if (!files.hasNext()) {
                    break;
                }
                LocatedFileStatus next = files.next();
                FileNodeVO fileNode = FileNodeVO.builder().fileName(next.getPath().getName()).updateTime( new Date(next.getModificationTime())).isFolder(next.isDirectory()).build();
                if(!fileNode.getIsFolder()){
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
                }
                fileList.add(fileNode);
            } catch (IOException e) {
                log.error("file-center:getFile:%s", e);
            }

        }
        return fileList;
    }
}