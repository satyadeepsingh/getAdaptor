package org.infozech.netty.logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;

public class CustomAppender extends AbstractAppender {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> list = new ArrayList<String>();
	
	protected CustomAppender(String name, Filter filter, Layout<? extends Serializable> layout,
			boolean ignoreExceptions) {
		super(name, filter, layout, ignoreExceptions);
		
	}

	public CustomAppender(String name, Filter filter, Layout<? extends Serializable> layout) {
        super(name, filter, layout);
    }

	@Override
	public void append(LogEvent event) {
		byte[] data = getLayout().toByteArray(event);
		list.add(new String(data).trim());
		

	}
	
	@Override
	public void stop(){
		System.out.println(list);
	}

}
