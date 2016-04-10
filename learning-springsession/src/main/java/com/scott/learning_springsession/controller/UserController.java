package com.scott.learning_springsession.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user/")
public class UserController {

	@RequestMapping(value = "/first", method = RequestMethod.GET)
	public Map<String, Object> firstResp(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		map.put("request Url", request.getRequestURL());
		request.getSession().setAttribute("map", map);
		return map;
	}

	@RequestMapping(value = "/sessions", method = RequestMethod.GET)
	public Object sessions(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		map.put("sessionId", request.getSession().getId());
		map.put("message", request.getSession().getAttribute("map"));

		return map;
	}

	@RequestMapping(value = "/login/{userName}/{device}", method = RequestMethod.GET)
	public String login(HttpServletRequest request, @PathVariable("userName") String userName, @PathVariable("device") String device) {
		Map<String, Object> map = new HashMap<>();
		request.getSession().setAttribute("userName", userName);
		request.getSession().setAttribute("device", device);
		return "userName:" + userName + ", device:" + device;
	}

	@RequestMapping(value = "/validate/{userName}/{device}", method = RequestMethod.GET)
	public Object validate(HttpServletRequest request, @PathVariable("userName") String userName, @PathVariable("device") String device) {
		if (request.getSession(false) == null)
			return "session expired";
		
		Map<String, Object> map = new HashMap<>();
		map.put("sessionId", request.getSession(false).getId());
		map.put("userName", request.getSession(false).getAttribute("userName"));
		map.put("device", request.getSession(false).getAttribute("device"));
		return map;
	}
}
