package HTTP;
/**
 * ��������Ӧ�ͻ��˵�һ������
 * ���������еĸ�������
 * �������е�����ʽ\·��\�汾\ ��Ϣͷ�е���Ϣ��
 * @author xzy
 *
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
	private String method;
	private String uri;
	private String protocol;
	private String requestUri;
	private String queryString;
	private Map<String,String> parameters=new HashMap<String, String>();
	private Map<String, String> headers=new HashMap<String, String>();
	private Socket socket;
	private InputStream is;
	public HttpRequest(Socket socket) throws EmptyRequestException {
		try {
			this.socket=socket;
			this.is=socket.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("httpRequest:��ʼ��������.....");
		//1.����������
		parseRequestLine();
		//2.������Ϣͷ
		parseHeaders();
		//3.������Ϣ����
		parseContent();
		System.out.println("httpRequest:�����������.....");
	}
	public void parseUri() {
		System.out.println("��һ������uri");
		if(uri.indexOf("?")!=-1) {
			//��
			String[] data=uri.split("\\?");
			requestUri=data[0];
			if(data.length>1) {
				queryString=data[1];
				String[] dd=queryString.split("&");
				for (String str : dd) {
					String[] s = str.split("=");
					if(s.length>1) {
						parameters.put(s[0], s[1]);
						System.out.println(s[0]+":"+s[1]);
					}else {
						parameters.put(s[0], null);
					}
				}
			}
 			}else {
			//û��
			requestUri=uri;
		}
	}
	//1 ����������
	public  void parseRequestLine() throws EmptyRequestException {
		try {
			System.out.println("��ʼ����������");
			String line=readLine();
			System.out.println("��������ϢΪ:"+line);
			//�����������ϢΪ��("")
			if("".equals(line)) {
				throw new EmptyRequestException();
			}
			String[] data=line.split("\\s");
			method=data[0];
//			uri= data[1];
			uri=URLDecoder.decode(data[1], "GBK");
			parseUri();//��һ������URI
			protocol=data[2];
			System.out.println("�����н�������");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (EmptyRequestException e) {
			throw e;
		}
	}
	//2 ������Ϣͷ
	public void parseHeaders() {
		System.out.println("��ʼ������Ϣͷ");
		try {
			while(true) {
				String line=readLine();
				if("".equals(line)) {
					break;
				}
				String[] data=line.split(":\\s");
				headers.put(data[0], data[1]);
				System.out.println("��Ϣͷ:"+line);
			}
			System.out.println(headers);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("��Ϣͷ��������");
	}
	//3 ������Ϣ����
	public void parseContent() {
		System.out.println("��ʼ������Ϣ����");
		System.out.println("��Ϣ���Ľ�������");
	}
	//�ӿͻ��˷��͹����������ȡһ����Ϣ
	public String readLine() throws IOException {
		//����һ���ɱ䳤���ַ�������һ������
		StringBuilder builder=new StringBuilder();
		int d=-1;
		char c1='a',c2='a';
		while((d=is.read())!=-1) {
			c2=(char)d;
			//�ж��Ƿ���
			if(c1==13&&c2==10) {
				break;
			}
			builder.append(c2);
			//��c2����c1��Ϊ�´�ѭ������һ���ַ�
			c1=c2;
		}
		return builder.toString().trim();
	}
	public String getMethod() {
		return method;
	}
	public String getUri() {
		return uri;
	}
	public String getProtocol() {
		return protocol;
	}


	//��дһ����headers�и��ݼ���ȡֵ�÷���
	public String getHeaders(String key) {
		return headers.get(key);
	}


	public String getRequestUri() {
		return requestUri;
	}
	public String getQueryString() {
		return queryString;
	}


	//��дһ����parameters�л��value�ķ���
	public String getParameter(String key) {
		return parameters.get(key);
	}
	
	
	
}
