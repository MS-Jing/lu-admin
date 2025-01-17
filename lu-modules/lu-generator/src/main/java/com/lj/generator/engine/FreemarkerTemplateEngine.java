package com.lj.generator.engine;

import cn.hutool.core.text.StrPool;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author luojing
 * @since 2024/11/19 17:40
 */
@Component
public class FreemarkerTemplateEngine implements TemplateEngine {

    private Configuration configuration;

    @Override
    public void init() {
        configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding(StandardCharsets.UTF_8.name());
        configuration.setClassForTemplateLoading(this.getClass(), StrPool.SLASH);
    }

    public FreemarkerTemplateEngine(){
        this.init();
    }

    @Override
    public void writer(Map<String, Object> dataModel, String templatePath, File outputFile) {
        this.writer((Object) dataModel, templatePath, outputFile);
    }

    @Override
    public void writer(Object dataModel, String templatePath, File outputFile) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
            Template template = configuration.getTemplate(templatePath);
            try {
                template.process(dataModel, new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            } catch (TemplateException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String preview(Map<String, Object> dataModel, String templatePath) {
        return this.preview((Object) dataModel, templatePath);
    }

    @Override
    public String preview(Object dataModel, String templatePath) {
        StringWriter writer = new StringWriter();
        try {
            configuration.getTemplate(templatePath).process(dataModel, writer);
        } catch (TemplateException | IOException e) {
            throw new RuntimeException(e);
        }
        return writer.toString();
    }
}
