package HTTP;
/**
 * ��Ӧ�ͻ�������Ķ���
 * ÿһ�������Ӧһ����Ӧ
 * ��Ӧ����������²���
 * ״̬�У���Ӧͷ����Ӧ����
 * @author xzy
 *
 */


import core.WebServer;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class HttpResponse<T> {
	private int statusCode=200;
	private String statusReason="OK";
	private Map<String, String> headers=new HashMap<String, String>();
	private File entity;
	private Socket socket;
	private OutputStream out;
	private T data;
	public HttpResponse(Socket socket) {
		try {
			this.socket=socket;
			this.out=socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusReason() {
		return statusReason;
	}
	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}
	public File getEntity() {
		return entity;
	}
	public void setEntity(File entity) {
		this.entity = entity;
		String fname=entity.getName();
		int index=fname.lastIndexOf(".");
		String ext=fname.substring(index+1);
		String mime= WebServer.mimeMapping.get(ext);
		putHeader("Content-Type",mime);
		putHeader("Content-Length",entity.length()+"");
		putHeader("Connection","keep-alive");//Connection: keep-alive
	}
	public void putHeader(String key,String value) {
		this.headers.put(key, value);
	}
	public void flush() {
		try {
			sendLine();
			sendHeaders();
			if (entity!=null&&entity.exists()){
				sendContent();//�����ļ�
			}else if (data!=null){
				sendContentJson();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sendLine() {
		try {
			//����״̬��
			String line="HTTP/1.1 "+statusCode+" "+statusReason;
			out.write(line.getBytes("ISO8859-1"));
			out.write(13);
			out.write(10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sendHeaders() {
		try {
			//����headers,����ÿ����Ӧͷ��Ϣ
			for(Entry<String, String> e:headers.entrySet()) {
				String key=e.getKey();
				String value=e.getValue();
				String line=key+": "+value;
				System.out.println("��Ӧͷ:"+line);
				out.write(line.getBytes("ISO8859-1"));
				out.write(13);
				out.write(10);
			}
			out.write(13);
			out.write(10);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void sendContent() {
		try(FileInputStream fis=new FileInputStream(entity)){
			byte[] data=new byte[1024*10];
			int len=-1;
			while((len=fis.read(data))!=-1) {
				out.write(data,0,len);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setData(T data) throws UnsupportedEncodingException {
		this.data = data;
		putHeader("Content-Type","application/json");
		putHeader("Content-Length",data.toString().getBytes().length+"");
		putHeader("Connection","keep-alive");//Connection: keep-alive
	}
	public void sendContentJson(){
		try{
			System.out.println("data:"+data.toString());
			System.out.println("data==null:"+"".equals(data));
			if (data!=null){
				OutputStreamWriter ows=new OutputStreamWriter(out);
				BufferedWriter bw=new BufferedWriter(ows);
				bw.write(data.toString());
				bw.close();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
