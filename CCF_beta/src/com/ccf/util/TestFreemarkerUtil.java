package com.ccf.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;

import org.apache.struts2.ServletActionContext;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TestFreemarkerUtil {
	
	public void init(String ftl, String htmlName, SimpleHash map, String relativePath)  
            throws IOException, TemplateException {
		Configuration cfg = new Configuration();
		
		/*System.out.println("path:" + ServletActionContext.getServletContext().getRealPath("/"));*/
		String ppath = ServletActionContext.getServletContext().getRealPath("/") + relativePath + "\\";
		System.out.println("ppath:" + ppath);
		FileTemplateLoader loader = new FileTemplateLoader(new File(ppath));
		cfg.setTemplateLoader(loader);
		cfg.setEncoding(Locale.getDefault(), "utf-8");
		Template template = cfg.getTemplate(ftl, "utf-8");
		
		String path = ServletActionContext.getServletContext().getRealPath("/");
		
		File htmlFile = new File(path + htmlName);
		Writer out = new BufferedWriter(new OutputStreamWriter(  
                new FileOutputStream(htmlFile), "utf-8")); 
		
		template.process(map, out);
		out.flush();
		out.close();
	}
	
	public void initUserList(String ftl, String htmlName, SimpleHash map, String relativePath)  
            throws IOException, TemplateException {
		Configuration cfg = new Configuration();
		
		/*System.out.println("path:" + ServletActionContext.getServletContext().getRealPath("/"));*/
		String ppath = ServletActionContext.getServletContext().getRealPath("/") + relativePath + "\\";
		System.out.println("path:" + ppath);
		FileTemplateLoader loader = new FileTemplateLoader(new File(ppath));
		cfg.setTemplateLoader(loader);
		cfg.setEncoding(Locale.getDefault(), "utf-8");
		Template template = cfg.getTemplate(ftl, "utf-8");
		
		String path = ServletActionContext.getServletContext().getRealPath("/");
		
		File htmlFile = new File(path + htmlName);
		Writer out = new BufferedWriter(new OutputStreamWriter(  
                new FileOutputStream(htmlFile), "utf-8")); 
		
		template.process(map, out);
		out.flush();
		out.close();
	}
	
}
