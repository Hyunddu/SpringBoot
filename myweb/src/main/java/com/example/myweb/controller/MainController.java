package com.example.myweb.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	
	@GetMapping("/")		// 기본경로로 해당 메서드 실행
	public String home() {
		WriteLog w1 = new WriteLog();
		w1.writeLog("홈페이지로 이동\n");
		return "index";		// index.html 파일 실행
	}
	
	@GetMapping("/another")
	public String anotherPage(Model model) {
		WriteLog w1 = new WriteLog();
		w1.writeLog("another페이지로 이동\n");
		String msg1 = w1.readText();
		model.addAttribute("send1",msg1);
		return "another";
	}
	
	@GetMapping("/another/result")
	public String result(@RequestParam("input-name") String name, Model model) {
		// Model : Java -> HTML 송신
		// @RequestParam : HTML -> Java 수신
		model.addAttribute("send1",name);
		return "result";
	}
}

class WriteLog{
	public void writeLog(String page) {
		FileOutputStream fout = null;
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		String times = sdf.format(date);
		String msg = "["+times+"] "+page;
		
		byte byteString[] = msg.getBytes();
		try {
			fout = new FileOutputStream("c:\\java\\log.txt",true);
			fout.write(byteString);
			fout.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String readText() {
		FileInputStream fin = null;
		int read = -1;
		String str1 = "";
		try {
			fin = new FileInputStream("c:\\java\\info.txt");
			InputStreamReader reader = new InputStreamReader(fin,"UTF-8");
			BufferedReader in = new BufferedReader(reader);
			// 파일 : 바이트(한글자), 자바 : 문자열(여러글자)
			while((read=in.read()) != -1) {
				str1 += (char)read;
			}
			fin.close();
			System.out.println(str1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str1;
	}
}
