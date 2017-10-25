package com.zqkj.bsm.downmodel.beanfactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;
import com.zqkj.bsm.downmodel.annotation.ModelTable;

public class ModelFactory {
	private static final String PACKAGE_ROOT = "com.zqkj.bsm.downmodel.bean";
	
	private static Map<String,Map<String,String>> models = new HashMap<String, Map<String,String>>();
	
	public static Map<String,String> getModelBean(String beanName)
	{
		if(models.containsKey(beanName)){
			return models.get(beanName);
		}else{
			Map<String,String> obj = createModelBean(beanName);
			models.put(beanName, obj);
			return obj;
		}
	}
	
	private static Map<String,String> createModelBean(String beanName){
		try {
			Map<String,String> m = new LinkedHashMap<String,String>();
			Class clazz = Class.forName(PACKAGE_ROOT+"."+stringFirstUpper(beanName));
			ModelTable c = (ModelTable)clazz.getAnnotation(ModelTable.class);
			m.put(clazz.getSimpleName(), c.value());
			m.put(c.value(), c.describe());
			Field[] fields = clazz.getDeclaredFields();
			Field.setAccessible(fields, true);
			for (Field field : fields) {
				ModelTable anno = field.getAnnotation(ModelTable.class);
				m.put(anno.value(), anno.describe());
			}
			return m.size() == 0 ? null : m;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String stringFirstUpper(String name){
		if(name == null || "".equals(name))
			return name;
		return name.substring(0, 1).toUpperCase()+name.substring(1);
	}
}
