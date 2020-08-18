package com.hnit.disk.config;

import com.hnit.disk.hadoop.HdfsClient;
import com.hnit.disk.hadoop.HdfsFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: liguangming
 * @Date: 2020/8/17
 */
@Configuration
public class HdfsConfig {
    @Value("${hadoop.hdfs.ip}")
    private  String hdfsServerIp;

    @Value("${hadoop.hdfs.port}")
    private  String hdfsServerPort;

    private long minEvictableIdleTimeMillis = 60000;
    private long timeBetweenEvictionRunsMillis = 30000;
    private int numTestsPerEvictionRun = -1;

    @Bean(initMethod = "init", destroyMethod = "stop")
    public HdfsClient HdfsClient(){
        HdfsClient client = new HdfsClient();
        return client;
    }

    /**
     * TestWhileConfig - 在空闲时检查有效性, 默认false
     * MinEvictableIdleTimeMillis - 逐出连接的最小空闲时间
     * TimeBetweenEvictionRunsMillis - 逐出扫描的时间间隔(毫秒) 如果为负数则不运行逐出线程，默认-1
     * NumTestsPerEvictionRun - 每次逐出检查时 逐出的最大数目
     * */
    @Bean
    public HdfsPoolConfig HdfsPoolConfig(){
        HdfsPoolConfig hdfsPoolConfig = new HdfsPoolConfig();
        hdfsPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        hdfsPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        hdfsPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        return hdfsPoolConfig;
    }

    @Bean
    public HdfsFactory HdfsFactory(){
        return  new  HdfsFactory("hdfs://" + hdfsServerIp + ":" + hdfsServerPort);
    }
}
