package com.code.generator.service;

import com.pengji.linker.baseentity.Page;
import com.pengji.linker.gencode.entity.GenTemplate;

/**
 * Created by lasia on 2020/3/18.
 */
public interface IGenTemplateService {

    GenTemplate get(String id);

    Page<GenTemplate> find(Page<GenTemplate> page, GenTemplate genTemplate);

    void save(GenTemplate genTemplate);

    void delete(GenTemplate genTemplate);
}
