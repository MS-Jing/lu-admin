package com.lj.generator.result.gen;

import lombok.Data;

/**
 * @author luojing
 * @since 2024/11/20 15:01
 * 包路径
 */
@Data
public class PackageInfo {
    /**
     * controller 包路径
     */
    private String controller;

    /**
     * service
     */
    private String service;
    private String serviceImpl;

    private String mapper;

    private String entity;
}
