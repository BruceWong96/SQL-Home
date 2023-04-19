package com.bruce.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户添加请求
 */
@Data
public class UserAddRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;

    private String userAccount;

    private String userAvatar;

    private Integer gender;

    private String userRole;

    private String userPassword;
}
