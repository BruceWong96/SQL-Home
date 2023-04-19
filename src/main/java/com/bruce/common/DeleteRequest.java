package com.bruce.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 删除请求
 *
 * @author Bruce Wong
 */
@Data
public class DeleteRequest implements Serializable {
    private Long id;

    private static final long serialVersionUID = 1L;
}
