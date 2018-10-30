package org.jtm.match.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchServiceController {
	@RequestMapping("/queryMatch")
	public String queryMathch(){
		
		return "你好，世界！";
	}
}
