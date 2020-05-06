/**
 *
 */
package com.code.generator.utils;

import com.code.generator.utils.exception.Exceptions;
import freemarker.template.Configuration;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;
import freemarker.template.Template;

/**
 * FreeMarkers工具类
 *
 * @author Michael
 * @version 2013-01-15
 */
public class FreeMarkers {

    public static String renderString(String templateString, Map<String, ?> model) {
        try {
            StringWriter result = new StringWriter();
            Template t = new Template("name", new StringReader(templateString), new Configuration());
            t.process(model, result);
            return result.toString();
        } catch (Exception e) {
            throw Exceptions.unchecked(e);
        }
    }

    public static String renderTemplate(Template template, Object model) {
        try {
            StringWriter result = new StringWriter();
            template.process(model, result);
            return result.toString();
        } catch (Exception e) {
            throw Exceptions.unchecked(e);
        }
    }

//    public static Configuration buildConfiguration(String directory) throws IOException {
//        Configuration cfg = new Configuration();
//        Resource path = new DefaultResourceLoader().getResource(directory);
//        cfg.setDirectoryForTemplateLoading(path.getFile());
//        return cfg;
//    }

}
