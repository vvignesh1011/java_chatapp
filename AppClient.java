package chatapp;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
public class AppClient implements Runnable {
	private static UserUi user;
	private DataInputStream din;
	private DataOutputStream dout;
	private Socket s;
	
	
	AppClient() {
		
		user=new UserUi(this);
		Thread t=new Thread(this);
		t.start();
		
	}
	
	
	

	@Override
	public void run() {
		try {
			String msg="";
			s=new Socket("localhost",5000);
			din=new DataInputStream(s.getInputStream());
			dout=new DataOutputStream(s.getOutputStream());
			do{
				msg=(String)din.readUTF();
				user.ta.append("\nServer :"+msg);
			}while(!msg.equals("over"));
			close();
			
		}
		catch (Exception e){
			System.out.println(e);
			
		}
		
	}//end of run
	
	
	public void close() {
		try {
		dout.close();
		s.close();
		
		}catch(Exception e) {System.out.println(e);
		
		}
		}




	public void send(String msg) {
		try {
			user.ta.append("\n you :"+msg);
			dout.writeUTF(msg);
			dout.flush();
			
			if(msg.equals("over")) 
				close();
				
			}catch(Exception e) {
			System.out.println(e);
		}
	}
}
