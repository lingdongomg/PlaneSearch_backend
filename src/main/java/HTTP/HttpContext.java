package HTTP;
/**
 * HTTP�涨�Ĺ̶�����ȫ���������
 * @author xzy
 *
 */

import core.ClientHandler;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HttpContext {

	private static Map<String , String > mimeMapping=new HashMap<String, String>();
	
	
	static {
		initMimeMapping();
	}


	public static void initMimeMapping() {
		try {
			SAXReader reader=new SAXReader();
			Document doc=reader.read(new File(ClientHandler.class.getClassLoader().getResource("./myweb/web.xml").toURI()));
			System.out.println(ClientHandler.class.getClassLoader().getResource("./myweb/web.xml").toURI());
			System.out.println("********************************************************************************************");
			Element root=doc.getRootElement();
			List<Element> list=root.elements("mime-mapping");
			for (Element el : list) {
				String key=el.elementText("extension");
				String value=el.elementText("mime-type");
				mimeMapping.put(key, value);
			}
			System.out.println(mimeMapping.size());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}
	
	public static String getMimeType(String ext) {
		return mimeMapping.get(ext);
	}

	public static void main(String... args) {
		System.out.println(mimeMapping.get("jpg"));
	}

	
}
