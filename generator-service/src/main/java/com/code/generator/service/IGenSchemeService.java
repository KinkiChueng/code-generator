package com.code.generator.service;

import com.pengji.linker.gencode.entity.baseentity.Page;
import com.pengji.linker.gencode.entity.GenScheme;

/**
 * 生成方案Service
 *
 * @author Michael
 * @version 2013-10-15
 */
public interface IGenSchemeService {

    public GenScheme get(String id);

    public Page<GenScheme> find(Page<GenScheme> page, GenScheme genScheme);

    public String save(GenScheme genScheme);

    public void delete(GenScheme genScheme);

}
