package com.hnit.disk.service;

import com.hnit.disk.response.FileNodeVO;

import java.io.IOException;
import java.util.List;

/**
 * @Author: liguangming
 * @Date: 2020/8/17
 */
public interface CatalogService {
    public Boolean isDir(String path);

    public Boolean dirExist(String path);

    public Boolean mkdir(String path);

    public List<FileNodeVO> getFiles(String path) throws IOException;

    public Boolean rename(String path, String newName, String oldName);
}
