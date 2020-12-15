package com.lck.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class CommonsResult<T> {
    private Integer id;
    private String message;
    private T data;
    //俩个参数的构造方法
    public CommonsResult(Integer id,String message){
        this(id,message,null);
    }
}
