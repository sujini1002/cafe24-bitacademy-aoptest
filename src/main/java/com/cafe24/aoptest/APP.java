package com.cafe24.aoptest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class APP {

	public static void main(String[] args) {
		//컨테이너 생성
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		ProductService ps = ac.getBean(ProductService.class); // ac 컨테이너에 스캔되어있는 것중(생성되어있는 것중) ProductService가 있는 것을 가져와봐
		ProductVo vo = ps.find("TV");
		System.out.println(vo);
	}

}
