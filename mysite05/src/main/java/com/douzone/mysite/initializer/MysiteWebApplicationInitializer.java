package com.douzone.mysite.initializer;

import javax.servlet.Filter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.douzone.mysite.config.Appconfig;
import com.douzone.mysite.config.Webconfig;

public class MysiteWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// Root Application Context
		return new Class<?>[] {Appconfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// Web Application Context
		return new Class<?>[] {Webconfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		// Servlet Mapping
		return new String[] {"/"};
	}

	@Override
	protected Filter[] getServletFilters() {
		// filters
		return new Filter[] {new CharacterEncodingFilter("UTF-8",true)};
	}

	@Override
	protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
		// create DispatchServlet
		DispatcherServlet dispatcherServlet = (DispatcherServlet) super.createDispatcherServlet(servletAppContext);
		
		// Exception Handler가 없으면 exception 발생
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		
		return dispatcherServlet;
	}
	
	

}
