package com.hnit.disk.em;

/**
 * @Author: liguangming
 * @Date: 2020/8/18
 */
public enum LenEnum {
    B(1024L),KB(1024L*1024L),MB(1024L*1024L*1024L),GB(1024L*1024L*1024L*1024L);
    private final Long len;
    private  LenEnum(Long len) {
        this.len = len;
    }
    public Long getLen(){
        return len;
    }
}
