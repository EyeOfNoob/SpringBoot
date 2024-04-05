package com.yedam.app.home;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpVO;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@Slf4j
@Controller
public class HomeController {
	
	//커맨드객체
	//QueryString =>커맨드객체
	//Method는 상관없음
	//Content-type : application/x-www-form-urlencoded
	@RequestMapping(path="comobj", method= {RequestMethod.GET, RequestMethod.POST})
	public String commandObject(@ModelAttribute("emp") EmpVO empVO) {
		log.info("path : /comobj");
		log.error("┌─┐ = employee_id : " + empVO.getEmployeeId());
		log.error("├─┘ = last_name : " + empVO.getLastName());
		log.error("└─┘ = ");
		log.info(empVO.toString());
		return "home";
		//classpath:/templates/home.html
	}
	
	@RequestMapping(path="reqparm", method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String requestParam(@RequestParam Integer employeeId, String lastName,
								@RequestParam(name="message",
											  defaultValue = "No message",
											  required=false) String msg) {
		log.info("path : /reqparm");
		log.error("┌─┐ = employee_id : " + employeeId);
		log.error("├─┘ = last_name : " + lastName);
		log.error("└─┘ = message : " + msg);
		return "";
	}
	
	//PathVariable
	//경로에 포함=>기본타입 매개변수
	//Method는 상관없음
	//Content-type은 상관없음
	//여러값을 보내야할때는 다른 Method와 같이 사용가능.(pathvariable만으로 여러값을 보내는건 잘 안함.)
	@RequestMapping(path="path/{id}1{no}1{code}")
	@ResponseBody
	public String pathVariable(@PathVariable String id,@PathVariable String no,@PathVariable String code) {
		log.info("path : /reqparm");
		log.error("┌─┘ = id : " + id);
		log.error("└─┐ = no : " + no);
		log.error("┌─┘ = code : " + code);
		return "";
	}
	
	//RequestBody를 사용시 post,put만 가능.(json처리를 위해 body를 요청하기때문.)
	@PostMapping("resbody")
	@ResponseBody
	public Map<String, Object> requestBody(@RequestBody EmpVO empVO) {
		Map<String, Object> map = new HashMap<>();
		map.put("path", "resbody");
		map.put("data", empVO);
		
		return map;
	}
}
