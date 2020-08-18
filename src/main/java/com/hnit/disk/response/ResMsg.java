package com.hnit.disk.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResMsg<T> {
    public static final Integer SUCCESS_CODE=0000;
    public static final Integer FAIL_CODE=9999;
    private int code;
    private T data;
    private String msg;


    public static<T> ResMsg<T> builderNotImplResp() {
        return ResMsg.<T>builder().code(FAIL_CODE).msg("未找到业务逻辑").build();
    }

    public static<T> ResMsg<T> builderSuccess(T t){
        return ResMsg.<T>builder().code(SUCCESS_CODE).msg("操作成功").data(t).build();
    }

    public static<T> ResMsg<T> builderSuccess(T t,String msg){
        return ResMsg.<T>builder().code(SUCCESS_CODE).msg(msg).data(t).build();
    }

    public static<T> ResMsg<T> builderFail(){
        return ResMsg.<T>builder().code(FAIL_CODE).msg("操作失败").build();
    }

    public static<T> ResMsg<T> builderFail(String msg){
        return ResMsg.<T>builder().code(FAIL_CODE).msg(msg).build();
    }
}
