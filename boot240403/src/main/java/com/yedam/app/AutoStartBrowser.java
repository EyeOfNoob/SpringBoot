package com.yedam.app;

import java.net.URI;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class AutoStartBrowser {

	private static boolean onServer = true;
	
	@PostConstruct
	public void init() {
		String url = "http://localhost:8080/comobj";
		
		if(onServer) {
			System.setProperty("java.awt.headless", "false");
			try {
				java.awt.Desktop.getDesktop().browse(URI.create(url));
			}catch(Exception e) {
				e.printStackTrace();
			}
			onServer = false;
		}
	}
}
