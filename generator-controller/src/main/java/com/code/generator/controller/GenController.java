package com.code.generator.controller;

import com.code.generator.service.IGenTemplateService;
import com.pengji.linker.gencode.entity.baseentity.Page;
import com.pengji.linker.gencode.entity.GenTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lasia
 * @date 2020/3/18
 */
@RestController
@RequestMapping(value = "${adminPath}/gen/genTemplate")
public class GenController extends BaseController {

    @Resource
    private IGenTemplateService genTemplateService;

    @RequestMapping(value = {"list", ""})
    public String list(GenTemplate genTemplate, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<GenTemplate> page = genTemplateService.find(new Page<GenTemplate>(request, response), genTemplate);
        model.addAttribute("page", page);
        return "modules/gen/genTemplateList";
    }

    @RequestMapping(value = "form")
    public String form(GenTemplate genTemplate, Model model) {
        model.addAttribute("genTemplate", genTemplate);
        return "modules/gen/genTemplateForm";
    }

    @RequestMapping(value = "save")
    public String save(GenTemplate genTemplate, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, genTemplate)) {
            return form(genTemplate, model);
        }
        genTemplateService.save(genTemplate);
        addMessage(redirectAttributes, "保存代码模板'" + genTemplate.getName() + "'成功");
        return "redirect:" + adminPath + "/gen/genTemplate/?repage";
    }

    @RequestMapping(value = "delete")
    public String delete(GenTemplate genTemplate, RedirectAttributes redirectAttributes) {
        genTemplateService.delete(genTemplate);
        addMessage(redirectAttributes, "删除代码模板成功");
        return "redirect:" + adminPath + "/gen/genTemplate/?repage";
    }

}
