package test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import attraction.AppSpring;
import attraction.config.AppConfig;

public class Test {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		ctx.getBeanFactory().createBean(AppSpring.class).test();
		//ctx.getBeanFactory().createBean(Injection.class).injectionSQL();
		ctx.close();		
	}

}
