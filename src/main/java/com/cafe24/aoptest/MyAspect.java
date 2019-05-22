package com.cafe24.aoptest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {
	//joinPoint,PointCut,Adivce를 하나의 클래스안에 있는 것 => Aspect
	
	//execution(리턴타입 메소드명(파라미터타임))- execution괄호안에 있는 메소드 실행전에 수행한다.
	@Before("execution(ProductVo com.cafe24.aoptest.ProductService.find(String))")
	public void beforeAdvice() {
		System.out.println("---before advice---");
	}
	
	//모든 리턴타입 모든패키지내(*..*.)의 ProductService의 모든메서드(.*(..)) 실행 후에 실행
	@After("execution(* *..*.ProductService.*(..))")
	public void afterAdvice() {
		System.out.println("---After advice---");
	}
	
	@AfterReturning("execution(* *..*.ProductService.*(..))")
	public void afterReturningAdvice() {
		System.out.println("---AfterReturning advice---");
	}
	
	//예외를 받는 변수명은 ex이다.
	@AfterThrowing(value="execution(* *..*.ProductService.*(..))",throwing="ex")
	public void afterThrowingAdvice(Throwable ex) {
		System.out.println("---AfterThrowing advice---\n"+ ex);
	}
	
	@Around(value="execution(* *..*.ProductService.*(..))")
	public Object roundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		
		//before advice
		System.out.println("--around(before) advice----");
		
//		point Cut 되는 메소드 호출
//		Object[] parameters = {"Camera"};
//		Object result = pjp.proceed(parameters); //TV대신 Camera가 나옴
		Object result = pjp.proceed();//advice를 할 메서드를(find()) 호출
		
		
		
		//after adivce
		System.out.println("--around(after) advice----");
		
		return result;
	}
}
