package com.yedam.app;

import java.net.URI;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

//@Component
public class AutoStartBrowser {

	//@PostConstruct
	public void init() {
		String url = "http://localhost:8080";
		System.setProperty("java.awt.headless", "false");
		try {
			java.awt.Desktop.getDesktop().browse(URI.create(url));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
