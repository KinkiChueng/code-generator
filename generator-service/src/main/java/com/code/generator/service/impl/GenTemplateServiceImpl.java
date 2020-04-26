package com.code.generator.service.impl;

import com.code.generator.service.IGenTemplateService;
import com.code.generator.utils.baseentity.Page;
import com.pengji.linker.gencode.entity.GenTemplate;
import io.micrometer.core.instrument.util.StringUtils;
import mapper.GenTemplateDao;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;


/**
 * 代码模板Service
 */
@Component
@EnableTransactionManagement
public class GenTemplateServiceImpl implements IGenTemplateService {

    @Autowired
    private GenTemplateDao genTemplateDao;

    @Override
    public GenTemplate get(String id) {
        return genTemplateDao.get(id);
    }

    @Override
    public Page<GenTemplate> find(Page<GenTemplate> page, GenTemplate genTemplate) {
        genTemplate.setPage(page);
        page.setList(genTemplateDao.findList(genTemplate));
        return page;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void save(GenTemplate genTemplate) {
        if (genTemplate.getContent() != null) {
            genTemplate.setContent(StringEscapeUtils.unescapeHtml4(genTemplate.getContent()));
        }
        if (StringUtils.isBlank(genTemplate.getId())) {
//            EntityUtils.preInsert(genTemplate);
            genTemplateDao.insert(genTemplate);
        } else {
//            EntityUtils.preUpdate(genTemplate);
            genTemplateDao.update(genTemplate);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(GenTemplate genTemplate) {
        genTemplateDao.delete(genTemplate);
    }

}
