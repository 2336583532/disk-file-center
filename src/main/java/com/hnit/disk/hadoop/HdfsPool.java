package com.hnit.disk.hadoop;


import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.AbandonedConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @Author: liguangming
 * @Date: 2020/8/17
 */
public class HdfsPool extends GenericObjectPool<Hdfs> {
    public HdfsPool(PooledObjectFactory<Hdfs> factory) {
        super(factory);
    }

    public HdfsPool(PooledObjectFactory<Hdfs> factory, GenericObjectPoolConfig<Hdfs> config) {
        super(factory, config);
    }

    public HdfsPool(PooledObjectFactory<Hdfs> factory, GenericObjectPoolConfig<Hdfs> config, AbandonedConfig abandonedConfig) {
        super(factory, config, abandonedConfig);
    }
}
