/**
 *
 */
package com.code.generator.service.service;

import com.code.generator.utils.Global;
import com.pengji.linker.gencode.entity.baseentity.DataEntity;
import com.pengji.linker.gencode.entity.baseentity.Page;
import mapper.CrudDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service基类
 *
 * @author Michael
 * @version 2014-05-16
 */
@Transactional(readOnly = true)
public abstract class CrudService<D extends CrudDao<T>, T extends DataEntity<T>> extends BaseService {

    /**
     * 持久层对象
     */
    @Autowired
    protected D dao;

    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    public T get(String id) {
        return dao.get(id);
    }

    /**
     * 获取单条数据
     *
     * @param entity
     * @return
     */
    public T get(T entity) {
        return dao.getByDao(entity);
    }

    /**
     * 查询列表数据
     *
     * @param entity
     * @return
     */
    public List<T> findList(T entity) {
        return dao.findList(entity);
    }

    /**
     * 查询分页数据
     *
     * @param page   分页对象
     * @param entity
     * @return
     */
    public Page<T> findPage(Page<T> page, T entity) {
        entity.setPage(page);
        page.setList(dao.findList(entity));
        return page;
    }

    public Page<T> findPageICanSee(Page<T> page, T entity) {
        entity.setPage(page);
        page.setList(dao.findIcanSeeList(entity));
        return page;
    }

    /**
     * 保存数据（插入或更新）
     *
     * @param entity
     */
    @Transactional(readOnly = false)
    public void
    save(T entity) {
        if (entity.getIsNewRecord()) {
            dao.insert(entity);
        } else {
            dao.update(entity);
        }
    }

    @Transactional(readOnly = false)
    public boolean update(T entity) {
        return dao.update(entity) > 0;
    }

    /**
     * 删除数据
     *
     * @param entity
     */
    @Transactional(readOnly = false)
    public void delete(T entity) {
        dao.delete(entity);
    }

    public String getGlobeConfig(String url) {
        return Global.getConfig(url);
    }
}
