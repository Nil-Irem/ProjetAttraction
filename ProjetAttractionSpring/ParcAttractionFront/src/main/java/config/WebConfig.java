package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import attraction.config.AppConfig;

@Configuration
@EnableWebMvc
@ComponentScan("controller")
@Import(AppConfig.class)
public class WebConfig implements WebMvcConfigurer {

	
	@Bean
	public UrlBasedViewResolver viewResolver() {
		UrlBasedViewResolver uBVR=new UrlBasedViewResolver();
		uBVR.setViewClass(JstlView.class);
		uBVR.setPrefix("/WEB-INF/views/"); 
		uBVR.setSuffix(".jsp"); 
		System.out.println(uBVR);
		return uBVR;
	}

}
