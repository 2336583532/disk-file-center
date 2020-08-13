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
    private static final Integer SUCCESS_CODE=0000;
    private static final Integer FAIL_CODE=9999;
    private int code;
    private T data;
    private String msg;
}
