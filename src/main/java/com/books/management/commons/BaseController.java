package com.books.management.commons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
	    @Autowired
		private HttpServletRequest request;
	    @Autowired
	    private HttpServletResponse response;
	    /**
	     * 接受参数
	     * @param name 参数值
	     * @return
	     */
	    public String getPara(String name){
			return request.getParameter(name);
		}
}
