package org.infozech.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Test {
	
	@Autowired
	BytesDao bytesdao;
	  
	public void get(){
		//System.out.println("Printing values...");
		
		List<Byte> byteslist = bytesdao.getBytelist();
		
		for(byte b: byteslist){
			System.out.printf("Value is... "+ String.format("%02X", b));
		}
	}
}    