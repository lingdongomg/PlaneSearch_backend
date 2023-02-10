package HTTP;
/**
 * 该类对象对应客户端的一次请求
 * 包含请求中的各种属性
 * 请求行中的请求方式\路径\版本\ 消息头中的信息等
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
		System.out.println("httpRequest:开始解析请求.....");
		//1.解析请求行
		parseRequestLine();
		//2.解析消息头
		parseHeaders();
		//3.解析消息正文
		parseContent();
		System.out.println("httpRequest:解析请求结束.....");
	}
	public void parseUri() {
		System.out.println("进一步解析uri");
		if(uri.indexOf("?")!=-1) {
			//有
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
			//没有
			requestUri=uri;
		}
	}
	//1 解析请求行
	public  void parseRequestLine() throws EmptyRequestException {
		try {
			System.out.println("开始解析请求行");
			String line=readLine();
			System.out.println("请求行信息为:"+line);
			//如果请求行信息为空("")
			if("".equals(line)) {
				throw new EmptyRequestException();
			}
			String[] data=line.split("\\s");
			method=data[0];
//			uri= data[1];
			uri=URLDecoder.decode(data[1], "GBK");
			parseUri();//进一步解析URI
			protocol=data[2];
			System.out.println("请求行解析结束");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (EmptyRequestException e) {
			throw e;
		}
	}
	//2 解析消息头
	public void parseHeaders() {
		System.out.println("开始解析消息头");
		try {
			while(true) {
				String line=readLine();
				if("".equals(line)) {
					break;
				}
				String[] data=line.split(":\\s");
				headers.put(data[0], data[1]);
				System.out.println("消息头:"+line);
			}
			System.out.println(headers);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("消息头解析结束");
	}
	//3 解析消息正文
	public void parseContent() {
		System.out.println("开始解析消息正文");
		System.out.println("消息正文解析结束");
	}
	//从客户端发送过来的请求读取一行信息
	public String readLine() throws IOException {
		//定义一个可变长度字符串接受一行文字
		StringBuilder builder=new StringBuilder();
		int d=-1;
		char c1='a',c2='a';
		while((d=is.read())!=-1) {
			c2=(char)d;
			//判断是否换行
			if(c1==13&&c2==10) {
				break;
			}
			builder.append(c2);
			//把c2付给c1作为下次循环的上一个字符
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


	//编写一个从headers中根据键获取值得方法
	public String getHeaders(String key) {
		return headers.get(key);
	}


	public String getRequestUri() {
		return requestUri;
	}
	public String getQueryString() {
		return queryString;
	}


	//编写一个从parameters中获得value的方法
	public String getParameter(String key) {
		return parameters.get(key);
	}
	
	
	
}
