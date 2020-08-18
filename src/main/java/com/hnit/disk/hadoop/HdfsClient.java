package com.hnit.disk.hadoop;


import com.hnit.disk.config.HdfsPoolConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.RemoteIterator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

/**
 * @Author: liguangming
 * @Date: 2020/8/17
 */
@Slf4j
public class HdfsClient {
    private HdfsPool hdfsPool;

    @Autowired
    private HdfsPoolConfig hdfsPoolConfig;

    @Autowired
    private HdfsFactory hdfsFactory;

    public void init(){
        hdfsPool = new HdfsPool(hdfsFactory,hdfsPoolConfig);
    }

    public void stop(){
        hdfsPool.close();
    }

    public Boolean isConnect(){
        Hdfs hdfs = null;
        try {
            hdfs = hdfsPool.borrowObject();
            return hdfs.isConnected();
        } catch (Exception e) {
            log.info("操作失败,%s",e);
        }finally {
            if (null != hdfs) {
                hdfsPool.returnObject(hdfs);
            }
        }
        return false;
    }

    public Boolean exist(String path){
        Hdfs hdfs = null;
        try {
            hdfs = hdfsPool.borrowObject();
            return hdfs.exist(path);
        } catch (Exception e) {
            log.info("操作失败,%s",e);
        }finally {
            if (null != hdfs) {
                hdfsPool.returnObject(hdfs);
            }
        }
        return false;
    }

    public Boolean isDir(String path){
        Hdfs hdfs = null;
        try {
            hdfs = hdfsPool.borrowObject();
            return hdfs.isDir(path);
        } catch (Exception e) {
            log.info("操作失败,%s",e);
        }finally {
            if (null != hdfs) {
                hdfsPool.returnObject(hdfs);
            }
        }
        return false;
    }

    public Boolean mkdir(String path) {
        Hdfs hdfs = null;
        try {
            hdfs = hdfsPool.borrowObject();
            return hdfs.mkdir(path);
        } catch (Exception e) {
            log.info("操作失败,%s",e);
        }finally {
            if (null != hdfs) {
                hdfsPool.returnObject(hdfs);
            }
        }
        return false;
    }

    public RemoteIterator<LocatedFileStatus> getFiles(String path){
        Hdfs hdfs = null;
        try {
            hdfs = hdfsPool.borrowObject();
            return hdfs.getFiles(path);
        } catch (Exception e) {
            log.info("操作失败,%s",e);
        }finally {
            if (null != hdfs) {
                hdfsPool.returnObject(hdfs);
            }
        }
        return null;
    }
}
