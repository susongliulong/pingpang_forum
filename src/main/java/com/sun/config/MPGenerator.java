package com.sun.config;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.sun.util.YamlReader;


import java.util.Collections;

public class MPGenerator {

    public static void main(String[] args) {

        String url = (String) YamlReader.instance.getValueByKey("spring.datasource.url");
        String username = (String) YamlReader.instance.getValueByKey("spring.datasource.username");
        String password = (String) YamlReader.instance.getValueByKey("spring.datasource.password");
        String outputDir = (String) YamlReader.instance.getValueByKey("mp.outputDir");

        FastAutoGenerator.create(url,username,password)
                .globalConfig(builder -> {
                    builder.author("sun") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(outputDir); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.sun") // 设置父包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, outputDir)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("admin")
                            .addInclude("album")
                            .addInclude("photograph");// 设置需要生成的表名
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
