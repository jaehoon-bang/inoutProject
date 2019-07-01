package kr.maxted.tamtam.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import kr.maxted.tamtam.core.interceptor.TamtamInterceptor;

@Configuration
public class TamtamConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
    private TamtamInterceptor tamtamInterceptor;
	
	/**
	 * welcome page
	 */
	@Override
    public void addViewControllers(ViewControllerRegistry registry ) {
		registry.addViewController("/").setViewName("forward:/admin/demo/page");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		super.addViewControllers(registry);
    }

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tamtamInterceptor)
                .addPathPatterns("/**")
//                .excludePathPatterns("/api/**/")
                .excludePathPatterns("/users/login"); //로그인 쪽은 예외처리를 한다.
    }
}