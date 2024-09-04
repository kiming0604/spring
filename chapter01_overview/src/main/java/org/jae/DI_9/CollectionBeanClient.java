package org.jae.DI_9;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CollectionBeanClient {
	public static void main(String[] args) {
		AbstractApplicationContext ctx = 
				 new GenericXmlApplicationContext("applicationContext9.xml");
		
		//List 객체 가져오기
		CollectionBean bean1 =  ctx.getBean("cBean1", CollectionBean.class);
		List<String> list = bean1.getAddressList();
		for (String address : list) {
			System.out.println(address);
		}
		
		System.out.println("----------------------------------------------------");
		
		CollectionBean bean2 = ctx.getBean("cBean2", CollectionBean.class);
		Set<String> set = bean2.getAddressSet();

		Iterator<String> setIterator = set.iterator();
		while (setIterator.hasNext()) {
		    String address = setIterator.next();
		    System.out.println(address);
		}

		System.out.println("----------------------------------------------------");
	
		CollectionBean bean3 = ctx.getBean("cBean3", CollectionBean.class);
		Map<String, String> map = bean3.getAddressMap();

		Iterator<Map.Entry<String, String>> mapIterator = map.entrySet().iterator();
		while (mapIterator.hasNext()) {
		    Map.Entry<String, String> entry = mapIterator.next();
		    System.out.println(entry.getKey() + " : " + entry.getValue());
		}


	}

}
