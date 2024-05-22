package chatapp;
import javax.swing.*;
import java.awt.event.*;

//uses to create a user interface
public class UserUi extends JFrame implements WindowListener,ActionListener,KeyListener {
	private AppServer apps;
	private AppClient appc;
	JTextArea ta=new JTextArea("");
	JTextField tf=new JTextField();
	
	JButton b=new JButton("send");
	
	public UserUi(AppServer app) {
		this.apps=app;
		create("server");
		b.addActionListener(this);
		tf.addKeyListener(this);
		}
	public UserUi(AppClient app) {
		this.appc=app;
		create("Client");
		b.addActionListener(this);
		tf.addKeyListener(this);
		}
	
	
	public void create(String head){
		
		ta.setBounds(20,20,300,350);
		tf.setBounds(20,380,240,40);
		b.setBounds(250,380,70,40);
		ta.setEditable(false);
		ta.getWrapStyleWord();
		
		
		add(b);
		add(ta);
		add(tf);
		addWindowListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(360,500);
		setTitle(head);
		setLayout(null);
		setVisible(true);
			
		
	}  
	
	
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("window opened");
		//for testing
		tf.requestFocus();
		
	}


	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("window is closing");
		if(apps instanceof AppServer)
			apps.close();
		if(appc instanceof AppClient)
			appc.close();
	
		
	}


	@Override
	public void windowClosed(WindowEvent e) {
	}


	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void send() {
		String msg=tf.getText();
		if(msg != "") {
		tf.setText(null);
		if(apps instanceof AppServer) {
		apps.send(msg);
		}
		if(appc instanceof AppClient) {
			appc.send(msg);
		}
	}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		send();		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
			send();		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



}
