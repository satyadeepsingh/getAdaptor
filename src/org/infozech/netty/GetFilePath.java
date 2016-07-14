package org.infozech.netty;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class GetFilePath {
        
	public String getFilePath(String classpathRelativePath) throws IOException{
		
		Resource resource = new ClassPathResource(classpathRelativePath);
		return resource.getFile().getAbsolutePath();
	}
}
