package com.code.generator.service;

import com.pengji.linker.gencode.entity.baseentity.Page;
import com.pengji.linker.gencode.entity.GenConfig;
import com.pengji.linker.gencode.entity.GenTable;

import java.util.List;

/**
 * 业务表Service
 *
 * @author Michael
 * @version 2013-10-15
 */

public interface IGenTableService {

    public GenTable get(String id);

    public Page<GenTable> find(Page<GenTable> page, GenTable genTable);

    public List<GenTable> findAll();

    /**
     * 获取物理数据表列表
     *
     * @param genTable
     * @return
     */
    public List<GenTable> findTableListFormDb(GenTable genTable);

    /**
     * 验证表名是否可用，如果已存在，则返回false
     *
     * @param genTable
     * @return
     */
    public boolean checkTableName(String tableName);

    /**
     * 获取物理数据表列表
     *
     * @param genTable
     * @return
     */
    public GenTable getTableFormDb(GenTable genTable);

    public void save(GenTable genTable);

    public void delete(GenTable genTable);

    public GenConfig getConfig();

}
