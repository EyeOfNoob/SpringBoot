package com.yedam.java.emp.mapper;

import java.util.List;

import com.yedam.java.emp.service.EmpVO;

//interface는 bean사용X(annotation)
public interface EmpMapper {
	public List<EmpVO> selectEmpAll();
}
