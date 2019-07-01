package kr.maxted.tamtam.admin.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 데모
 * @author devkimsj
 *
 */
@Controller
@RequestMapping("/admin")
public class DemoController {
	
	@RequestMapping("/demo/page")
	public ModelAndView page1(ModelAndView modelAndView) {
		modelAndView.addObject("name", "devkimsj");
		return modelAndView;
	}
	
	@RequestMapping("/regist")
	public String page2() {
		return "hello";
	}
	
}