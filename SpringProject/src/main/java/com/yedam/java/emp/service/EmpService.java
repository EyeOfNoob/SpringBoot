package com.yedam.java.emp.service;

import java.util.List;

//interface는 bean사용X(annotation)
public interface EmpService {
	public List<EmpVO> empAllList();
}
