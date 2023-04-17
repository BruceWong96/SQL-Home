package com.bruce.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 词库
 * @TableName dict
 */
@TableName("dict")
@Data
public class Dict implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 词库 ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 词库名称
     */
    private String name;
    /**
     * 词库内容
     */
    private String content;
    /**
     * 词库审核状态，0：未审核，1：已审核，2: 拒绝
     */
    private Integer reviewStatus;
    /**
     * 词库审核消息
     */
    private String reviewMessage;
    /**
     * 创建词库的用户 ID
     */
    private Long userId;
    /**
     * 词库创建时间
     */
    private Date createTime;
    /**
     * 词库更新时间
     */
    private Date updateTime;
    /**
     * 词库是否已删除，0：未删除，1：已删除
     */
    private Integer isDelete;
}
