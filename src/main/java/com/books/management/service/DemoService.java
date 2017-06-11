package com.books.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.management.domain.Demo;
import com.books.management.mapper.DemoMapper;

@Service
public class DemoService {
	@Autowired
	private DemoMapper  demoMapper;
	
	public List<Demo> getDemo(){
		return demoMapper.selectAll();
	}
}
