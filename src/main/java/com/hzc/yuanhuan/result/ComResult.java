package com.hzc.yuanhuan.result;

import lombok.Data;

@Data
public class ComResult<T> {

    private Integer state;

    private String message;

    private T data;

    public ComResult(Integer state, String message, T data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }
}
