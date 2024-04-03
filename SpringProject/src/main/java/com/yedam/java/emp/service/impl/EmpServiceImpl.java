package com.yedam.java.emp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.java.emp.mapper.EmpMapper;
import com.yedam.java.emp.service.EmpService;
import com.yedam.java.emp.service.EmpVO;

@Service
public class EmpServiceImpl implements EmpService{

	@Autowired
	EmpMapper empMapper; // <=필드 주입방식
	
//	EmpMapper empMapper;
//	
//	@Autowired
//	public EmpServiceImpl(EmpMapper empMapper) { //<=생성자 주입방식
//		this.empMapper = empMapper;
//	}
	
	@Override
	public List<EmpVO> empAllList() {
		return empMapper.selectEmpAll();
	}

}
