package com.cy.pj.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@ConfigurationProperties(prefix = "page.config")//告诉springboot读配置文件指定的前缀(用了这个注解后会警告,需要下载.xml下载即可)
@Configuration//描述此类交给spring管理
public class PaginationProperties {
		private Integer pageSize=10;//这个类的setter注解必须要加
}
