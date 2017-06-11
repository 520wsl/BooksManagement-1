package com.books.management.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.books.management.commons.BaseController;
import com.books.management.domain.Demo;
import com.books.management.service.DemoService;
import com.books.management.utils.RD;


/**
 * 测试类
 * @author LB
 */
@RestController
@RequestMapping(value="/")
public class DemoController extends BaseController{
	@Autowired
	private DemoService demoService;
	
	@RequestMapping(value="demo")
	public Map<String, Object> demo(){
		System.out.println("图书管理系统测试!!!");
		List<Demo> list=demoService.getDemo();
		return RD.success(list).renderJson(); 
	}
}
