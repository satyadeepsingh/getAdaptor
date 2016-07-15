package org.infozech.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BytesDao {
	
	private List<Byte> bytelist = new ArrayList<Byte>();

	public List<Byte> getBytelist() {
		return bytelist;
	}

	public void setBytelist(List<Byte> bytelist) {
		this.bytelist = bytelist;
	}
	
	
}
