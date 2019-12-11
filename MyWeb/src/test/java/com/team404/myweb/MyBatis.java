package com.team404.myweb;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appServlet/DB-context.xml")
public class MyBatis {
	
	@Autowired
	private DataSource ds;
	@Autowired
	private SqlSessionFactoryBean ssfb;
	
	@Test
	public void test() {
		
		try {
			
			System.out.println("ds: " + ds);
			System.out.println("sql: " + ssfb);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
}
