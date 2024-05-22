package chatapp;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class AppServer implements Runnable{
	private static UserUi user;
	private DataOutputStream dos;
	private ServerSocket ss;
	private Socket s;
	
	//constructor
	AppServer() {		
		user=new UserUi(this); 
		//to create a instance for a user ui 
		//and create user interface for a server
		Thread t1= new Thread(this);  
		//create a thread 
	    t1.start();       		
	    //it call the run() using thread instance t1
	}

	@Override
	public void run() { // method called when the thread called
		try {
			String msg="";
			ss=new ServerSocket(5000);	
			//create a server socket with the port no 5000 
			s=ss.accept();				
			//accept the connection when the client is connected
			DataInputStream dis=new DataInputStream(s.getInputStream()); 
			//create the data input stream object for the socket connection 
			dos=new DataOutputStream(s.getOutputStream());
			//create the data output stream object for the socket connection
			
			
			do{
			msg=(String)dis.readUTF();
			//read message from the client using dis and store to the msg variable
			user.ta.append("\n client :"+msg);	
			//append msg to the text area in the userUI
			}while(!msg.equals("over"));
			//do while loop run when the msg is equals to "over"
			close();
			//call the close function
			
			
					
	}//end of try
	catch(Exception e) {
		System.out.println(e);
	}//end of catch
	
	}
	
	public void send(String msg) {
		//method used to send msg to the client
		try {
			user.ta.append("\n you :"+msg);
			//append msg to the UI
			dos.writeUTF(msg);
			//write msg to the data output stream
			dos.flush();
			//clear data in the data output stream
		
		if(msg.equals("over")) 
			close();
		//if the send msg equals to "over" call the close()
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
	public void close() {
		try {
			if(dos!=null)
				dos.close();
				//check the dos created 
				//and close the data input stream
				ss.close();
				//close the server scoket
		
		}catch(Exception e) {System.out.println(e);
		
		}
		}
}