package getRequest;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JacksonDemo1 {
	
	public String toString() {
		
		return null;
	}
	
	 private Map<String,String> maps = new HashMap<String,String>();
	 
	
	@Test
	public void convertJavaObjectToJson() throws Exception{
	maps.put("name", "demo");
	maps.put("class", "automation");
	ObjectMapper objMapper = new ObjectMapper();
	String jsonOutput = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(maps);
	System.out.println(jsonOutput);
	}
	
	
	
	
	
	

}
