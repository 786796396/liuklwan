package com.ucap.cloud_web;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;


@SuppressWarnings("deprecation")
public class BaseTest extends AbstractDependencyInjectionSpringContextTests  {
	protected String[] getConfigLocations() {
//		this.setAutowireMode(AUTOWIRE_BY_NAME);
		return new String[] {"classpath:applicationContext.xml","classpath:applicationContext-quartz.xml","classpath:app-struts.xml" };
	}

	public void testBase() {
		System.out.println("-- spring load xml success--");
	}
}
