package com.mzl.core.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * @ClassName CodeGenerator1
 * @Description: TODO
 * @Author may
 * @Date 2019/12/10 15:44
 */
public class CodeGenerator1 {

    public static void main(String[] args) {

        String projectPath = System.getProperty("user.dir");
        String outputDir = projectPath + "/src/main/java"; //生成文件输出目录
        String author = "may"; //注释作者
        String entity = "ProductCategory"; //生成文件实体名称
        String entityPrefix = "tbl_"; //前缀
        String[] tableName = "fk_user,fk_role".split(","); //表名多个以 , 分割
        String packageName = "com.apple"; //生成文件的 包名

        //数据库
        String dbUrl = "jdbc:mysql://localhost:3306/cms?characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("may")
                .setPassword("123456")
                .setDriverName("com.mysql.cj.jdbc.Driver");


        GlobalConfig config = new GlobalConfig();
        config.setAuthor(author);
        config.setOutputDir(outputDir);
        config.setFileOverride(true);
        config.setOpen(false); //文件生成完是否打开目录
        config.setEntityName("%sModel");//实体类添加Model
        config.setServiceName("%sService");
        config.setControllerName("%sController");
        config.setServiceImplName("%sServiceImpl");
        config.setMapperName("%sMapper");
        config.setXmlName("%sMapper");

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(false) //设置是否使用 lombook
//                .setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setRestControllerStyle(true)
                .setControllerMappingHyphenStyle(true)
//                .setSuperEntityClass("com.baomidou.mybatisplus.extension.service.impl.ServiceImpl")
//                .setSuperMapperClass("com.baomidou.mybatisplus.core.mapper.BaseMapper")
                .setInclude(tableName)//修改替换成你需要的表名，多个表名传数组
                .setTablePrefix(entityPrefix)
        ;

        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                //do nothing
            }
        };

        PackageConfig packageConfig = new PackageConfig();
//        packageConfig.setModuleName("ProductCategory"); //在parent包下面添加的包名 eg: com.apple.ProductCategory
        packageConfig.setParent(packageName);
        packageConfig.setController("controller");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        packageConfig.setEntity("model");


//        TemplateConfig templateConfig  = new TemplateConfig();
//        templateConfig.setEntity("/templates/entity2.java");
//        templateConfig.setXml(null);

        AutoGenerator mpg = new AutoGenerator();
//        mpg.setTemplate(templateConfig);
        mpg.setGlobalConfig(config);
        mpg.setDataSource(dataSourceConfig);
        mpg.setStrategy(strategyConfig);
        mpg.setCfg(cfg);
        mpg.setPackageInfo(packageConfig);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
