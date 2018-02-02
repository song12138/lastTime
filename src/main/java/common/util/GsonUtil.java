package common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {
	public static Gson gson = new GsonBuilder()
	.enableComplexMapKeySerialization() 
    .serializeNulls()   
    .setDateFormat("yyyy-MM-dd hh:mm:ss")    //"2015-06-23"
    .setPrettyPrinting() 
    .setVersion(1.0)    
    .create();
	
	
//	public static Gson gsonUtil = new GsonBuilder()
//	.serializeNulls()
//	.setDateFormat("yyyy-MM-dd HH:mm:ss")
//	.setPrettyPrinting()
//	.setExclusionStrategies(new DefinedExclusionStrategy())
//	.enableComplexMapKeySerialization()
//	.create();
	
}
