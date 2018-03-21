package myClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
	public static void main(String[] args){
		Socket s=null;
		                                                                                                                                                                           = "���ڳ�˵��";
		try {
			 s = new Socket("192.168.1.112",30000);
			 new Thread(new KClient(s)).start();
			 new Thread(new Clientoutput(s)).start();
//			 BufferedReader tempRead = new BufferedReader(new InputStreamReader(System.in));
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class KClient implements Runnable{
	Socket s=null;
	BufferedReader temps = null;
	public KClient(Socket s)throws Exception{
		this.s=s;
		this.temps =new BufferedReader(new InputStreamReader(s.getInputStream()));
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String res = null;
			try {
				while((res = temps.readLine())!=null){
					System.out.println(res);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				try {
					temps.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	
}
class Clientoutput implements Runnable{
	Socket s= null;
	BufferedReader temp = null;
	PrintWriter pw = null;
	public Clientoutput(Socket s) throws Exception{
		this.s=s;
		this.temp = new BufferedReader(new InputStreamReader(System.in));
		this.pw= new PrintWriter(s.getOutputStream(),true);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String temp1 = null;
		try {
			while((temp1=temp.readLine())!=null){
				pw.println(s.getInetAddress()+"˵:"+temp1);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}