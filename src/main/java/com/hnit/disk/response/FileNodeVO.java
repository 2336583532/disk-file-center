package com.hnit.disk.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: liguangming
 * @Date: 2020/8/14
 */
@Data
public class FileNodeVO implements Serializable {
    private static final long serialVersionUID = 8483663883914634113L;

    private String fileName;

    private String fileSize;

    private Data updateTime;

    private Boolean isFolder;
}
