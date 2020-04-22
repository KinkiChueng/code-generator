package com.code.generator.service;

import com.code.generator.model.GenTemplate;


/**
 * Created by lasia on 2020/3/18.
 */
public interface IGenTemplateService {

    GenTemplate get(String id);

//    public Page<GenTemplate> find(Page<GenTemplate> page, GenTemplate genTemplate);

    void save(GenTemplate genTemplate);

    void delete(GenTemplate genTemplate);
}
