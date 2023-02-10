package core;/*package cn.whpu.webserver.core;
*//**
 * �����������·����Ӧ��servlet�����
 * @author xzy
 *
 *//*

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.whpu.webserver.http.HttpServlet;

public class ServletContext {
	// key:����·��   value:��Ӧ��Servlet����
	private static Map<String, HttpServlet> serlvetMapping=new HashMap<>();
	
	static {
		initServletMapping();
	}

	private static void initServletMapping() {
//		serlvetMapping.put("/myweb/reg",new RegServlet());
//		serlvetMapping.put("/myweb/login",new LoginServlet());
//		serlvetMapping.put("/myweb/password_update",new UpdateServlet());
		
		try {
			*//*
			 * ����conf/servlets.xml�ļ�
			 * ����ǩ�µ�<servlet>��ǩ��ȡ
			 * ���servlet��ǩ�е�path��className����
			 * ͨ��������servlet���󱣴浽map��
			 * "./conf/Servlets.xml"
			 *//*
			SAXReader reader=new SAXReader();
			Document doc=reader.read(new File(ClientHandler.class.getClassLoader().getResource("./conf/Servlets.xml").toURI()));
			Element el=doc.getRootElement();
			List<Element> list=el.elements();
			for (Element e : list) {
				//�Ȼ�÷���·��
				String path=e.attributeValue("path");
				String className=e.attributeValue("className");
				//ͨ��������ƻ��������ʵ��
				Class cls=Class.forName(className);
				HttpServlet value=(HttpServlet)cls.newInstance();
				serlvetMapping.put(path, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static HttpServlet getServlet(String path) {
		System.out.println("��������path:"+path);
		return serlvetMapping.get(path);
	}
}*/
