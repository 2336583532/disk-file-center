package com.hnit.disk.hadoop;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;

import java.io.IOException;
import java.net.URI;

/**
 * @Author: liguangming
 * @Date: 2020/8/17
 */
@Slf4j
public class Hdfs {
    private FileSystem fs;
    private String coreResource;
    private String hdfsResource;
    private final String url;
    private static final String NAME = "fs.hdfs.impl";

    public Hdfs(String url) {
        this.url = url;
    }

    public void open() {
        try {
            Configuration conf = new Configuration();
            fs=FileSystem.get(new URI(url),conf,"Admin");
            log.info("[Hadoop]创建实例成功");
        } catch (Exception e) {
            log.error("[Hadoop]创建实例失败", e);
        }
    }

    public void close() {
        try {
            if (null != fs) {
                fs.close();
                log.info("[Hadoop]关闭实例成功");
            }
        } catch(Exception e) {
            log.error("[Hadoop]关闭实例失败", e);
        }
    }

    public boolean isConnected() throws IOException {
        return fs.exists(new Path("/"));
    }

    public Boolean isDir(String path){
        try {
            return fs.isDirectory(new Path(path));
        } catch (IOException e) {
            log.error("hdfs-mkdir,%s",e);
        }
        return false;
    }

    public Boolean exist(String path){
        try {
            return fs.exists(new Path(path));
        } catch (IOException e) {
            log.error("hdfs-mkdir,%s",e);
        }
        return false;
    }

    public Boolean mkdir(String path){
        try {
            return fs.mkdirs(new Path(path));
        } catch (IOException e) {
            log.error("hdfs-mkdir,%s",e);
        }
        return false;
    }

    public RemoteIterator<LocatedFileStatus> getFiles(String path) throws IOException {
        return fs.listLocatedStatus(new Path(path));
    }
}
