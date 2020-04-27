package com.code.generator.service.impl;

import com.code.generator.service.IGenSchemeService;
import com.code.generator.utils.GenTemplate;
import com.code.generator.utils.GenUtils;
import com.pengji.linker.gencode.entity.baseentity.Page;
import com.code.generator.service.service.BaseService;
import com.pengji.linker.gencode.entity.*;
import mapper.GenSchemeDao;
import mapper.GenTableColumnDao;
import mapper.GenTableDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 生成方案Service
 *
 * @author Michael
 * @version 2013-10-15
 */
@Service
@Transactional(readOnly = true)
public class GenSchemeService extends BaseService implements IGenSchemeService {

    @Autowired
    private GenSchemeDao genSchemeDao;

    @Autowired
    private GenTableDao genTableDao;
    @Autowired
    private GenTableColumnDao genTableColumnDao;

    @Override
    public GenScheme get(String id) {
        return genSchemeDao.get(id);
    }

    @Override
    public Page<GenScheme> find(Page<GenScheme> page, GenScheme genScheme) {
        GenUtils.getTemplatePath();
        genScheme.setPage(page);
        page.setList(genSchemeDao.findList(genScheme));
        return page;
    }

    @Override
    @Transactional(readOnly = false)
    public String save(GenScheme genScheme) {
        if (StringUtils.isBlank(genScheme.getId())) {
            genSchemeDao.insert(genScheme);
        } else {
            genSchemeDao.update(genScheme);
        }
        // 生成代码
        if ("1".equals(genScheme.getFlag())) {
            return generateCode(genScheme);
        }
        return "";
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(GenScheme genScheme) {
        genSchemeDao.delete(genScheme);
    }

    private String generateCode(GenScheme genScheme) {

        StringBuilder result = new StringBuilder();

        // 查询主表及字段列
        GenTable genTable = genTableDao.get(genScheme.getGenTable().getId());
        genTable.setColumnList(genTableColumnDao.findList(new GenTableColumn(new GenTable(genTable.getId()))));

        // 获取所有代码模板
        GenConfig config = GenUtils.getConfig();

        // 获取模板列表
        List<com.code.generator.utils.GenTemplate> templateList = GenUtils.getTemplateList(config, genScheme.getCategory(), false);
        List<com.code.generator.utils.GenTemplate> childTableTemplateList = GenUtils.getTemplateList(config, genScheme.getCategory(), true);

        // 如果有子表模板，则需要获取子表列表
        if (childTableTemplateList.size() > 0) {
            GenTable parentTable = new GenTable();
            parentTable.setParentTable(genTable.getName());
            genTable.setChildList(genTableDao.findList(parentTable));
        }

        // 生成子表模板代码
        for (GenTable childTable : genTable.getChildList()) {
            childTable.setParent(genTable);
            childTable.setColumnList(genTableColumnDao.findList(new GenTableColumn(new GenTable(childTable.getId()))));
            genScheme.setGenTable(childTable);
            Map<String, Object> childTableModel = GenUtils.getDataModel(genScheme);
            for (GenTemplate tpl : childTableTemplateList) {
                result.append(GenUtils.generateToFile(tpl, childTableModel, genScheme.getReplaceFile()));
            }
        }

        // 生成主表模板代码
        genScheme.setGenTable(genTable);
        Map<String, Object> model = GenUtils.getDataModel(genScheme);
        for (com.code.generator.utils.GenTemplate tpl : templateList) {
            result.append(GenUtils.generateToFile(tpl, model, genScheme.getReplaceFile()));
        }
        return result.toString();
    }
}
