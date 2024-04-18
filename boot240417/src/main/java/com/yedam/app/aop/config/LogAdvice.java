package com.yedam.app.aop.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect //포인트컷 + 어드바이스 설정 빈
@Component
public class LogAdvice {
	// 포인트컷 : 비지니스 로직과 관련된 메소드 중에서 Advice(공통 코드)가 적용될 메서드
	@Pointcut("within(com.yedam.app.emp.service.impl.*)")
//			   within = 특정경로내 모든 메서드 지정
	public void allPointCut() {}
	
	// Weaving : 포인트컷 + Advice + 적용 타이밍
	@Around("allPointCut()")
	public Object logger(ProceedingJoinPoint joinPoint) throws Throwable{
//							지금 실행중인 비지니스 로직
		// Aop가 적용되는 메서드의 이름
		String signatuerStr = joinPoint.getSignature().toString();
		System.out.println("시작Around : " + signatuerStr);
		
		// 공통기능
		System.out.println("핵심 기능 전 실행 - 공통기능 : " + System.currentTimeMillis());
		
		try {
			// 비지니스 메소드를 실행
			Object obj = joinPoint.proceed();
			return obj;
		} finally{
			System.out.println("핵심 기능 후 실행 - 공통기능 : " + System.currentTimeMillis());
			System.out.println("끝Around : " + signatuerStr);
		}
	}
	
	@Before("allPointCut()")
	public void beforeAdvice(JoinPoint joinPoint){
		String signatuerStr = joinPoint.getSignature().toString();
		System.out.println("시작Before : " + signatuerStr);
	}
}
