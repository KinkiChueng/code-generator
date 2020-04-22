package com.code.generator.service.impl;


import com.code.generator.mapper.GenTemplateDao;
import com.code.generator.model.GenTemplate;
import com.code.generator.model.Page;
import com.code.generator.service.IGenTemplateService;
import com.code.generator.utils.EntityUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 代码模板Service
 */
@Service
@Transactional(readOnly = true)
public class GenTemplateService implements IGenTemplateService {

    @Autowired
    private GenTemplateDao genTemplateDao;

    public GenTemplate get(String id) {
        return genTemplateDao.get(id);
    }

    public Page<GenTemplate> find(Page<GenTemplate> page, GenTemplate genTemplate) {
        genTemplate.setPage(page);
        page.setList(genTemplateDao.findList(genTemplate));
        return page;
    }

    @Transactional(readOnly = false)
    public void save(GenTemplate genTemplate) {
        if (genTemplate.getContent() != null) {
            genTemplate.setContent(StringEscapeUtils.unescapeHtml4(genTemplate.getContent()));
        }
        if (StringUtils.isBlank(genTemplate.getId())) {
            EntityUtils.preInsert(genTemplate);
            genTemplateDao.insert(genTemplate);
        } else {
            EntityUtils.preUpdate(genTemplate);
            genTemplateDao.update(genTemplate);
        }
    }

    @Transactional(readOnly = false)
    public void delete(GenTemplate genTemplate) {
        genTemplateDao.delete(genTemplate);
    }

}
