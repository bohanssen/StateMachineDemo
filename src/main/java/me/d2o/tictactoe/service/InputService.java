package me.d2o.tictactoe.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

@Service
public class InputService {

	private InputStreamReader in;
	private BufferedReader bufferRead;
	
	@PostConstruct
	private void init(){
		in = new InputStreamReader(System.in);
		bufferRead = new BufferedReader(in);
	}
	
	@PreDestroy
	private void destroy() throws IOException{
		bufferRead.close();
		in.close();
	}
	
	public String getInput(String player){
		while (true){
			try{
				System.out.println(player+ " please input coordinates {A1}:");
				String inputstring = bufferRead.readLine().trim();
				Pattern p = Pattern.compile("[ABCabc..][123..]");
				Matcher m = p.matcher(inputstring);
				if (m.find()){
					return inputstring;
				} 
			} catch(Exception ex){

			}
		}
	}
}
