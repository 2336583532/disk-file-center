package com.hnit.disk.hadoop;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.io.IOException;

/**
 * @Author: liguangming
 * @Date: 2020/8/17
 */
public class HdfsFactory implements PooledObjectFactory<Hdfs> {

    private final String url;
    public HdfsFactory(String url){
        this.url = url;
    }


    @Override
    public PooledObject<Hdfs> makeObject() throws Exception {
        Hdfs hdfs = new Hdfs(url);
        hdfs.open();
        return new DefaultPooledObject<Hdfs>(hdfs);
    }

    @Override
    public void destroyObject(PooledObject<Hdfs> pooledObject) throws Exception {
        Hdfs hdfs = pooledObject.getObject();
        hdfs.close();
    }

    @Override
    public boolean validateObject(PooledObject<Hdfs> pooledObject) {
        Hdfs hdfs = pooledObject.getObject();
        try {
            return  hdfs.isConnected();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void activateObject(PooledObject<Hdfs> pooledObject) throws Exception {

    }

    @Override
    public void passivateObject(PooledObject<Hdfs> pooledObject) throws Exception {

    }
}
