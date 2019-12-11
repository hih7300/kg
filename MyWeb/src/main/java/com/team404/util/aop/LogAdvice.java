package com.team404.util.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAdvice {

	//이 프로젝트에 모든 서비스에 대해서, target, args, signature의 실행정보를 출력하는 aop
	
	// 로그를 찍을 있게 하는 처리하는 기능.
	private static final Logger log = LoggerFactory.getLogger(LogAdvice.class);
	
	@Around("execution(* com.team404.*.service.*ServiceImpl.*(..))")
	public Object aronudLog(ProceedingJoinPoint jp) {
		
		long start = System.currentTimeMillis();
		
		log.info("적용 클래스: " + jp.getTarget());
		log.info("적용 파라미터" + Arrays.toString(jp.getArgs()));
		log.info("적용 메서드" + jp.getSignature());
		
		Object result = null;
		try {
			result = jp.proceed(); // 이메서드를 만나야 서비스의 메서드가 실행됨.
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();

		log.info("메서드 수행시간: " + ((end - start) * 0.001) + "초");
		
		return result;
	}
	
	
}
