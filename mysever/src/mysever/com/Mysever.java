package mysever.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Mysever {
	
	public static void main(String[] args) {
	ServerSocket sc = null;
	try {
		sc = new ServerSocket(30000);
		while(true){
			Socket temp= sc.accept();	
			
			new Thread(new Server(temp)).start();
			new Thread(new Severoutput(temp)).start();
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			sc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	}
	
	
	
}
class Server implements Runnable{
	Socket s=null;
	BufferedReader bf = null;
	public Server(Socket s) throws Exception{
		this.s=s;
		this.bf = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			 String temp1;
			while((temp1=bf.readLine()) != null){
				System.out.println(temp1);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
class Severoutput implements Runnable{
	Socket s = null;
	BufferedReader input = null;
	PrintWriter pw=null;
	public Severoutput(Socket s) throws Exception{
		this.s=s;
		this.input = new BufferedReader(new InputStreamReader(System.in));
		this.pw = new PrintWriter(s.getOutputStream(),true);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub 
	
		try {
			String output = null;
		while((output=input.readLine())!=null){
			pw.println(s.getInetAddress()+"หตฃบ"+output);
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
