import java.lang.Thread;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.InetAddress;

import java.io.IOException;
import java.io.EOFException;

public class Client extends Thread{
	
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Socket connection;
	
	private String serverIP;
	private GameCode gameCode;
	private Player player;
	
	public Client(){
		
	}
	
	public void getNeededVars(String IP, GameCode gc, Player pl){
		serverIP = IP;
		gameCode = gc;
		player = pl;
	}
	
	public void run(){
		
		try{
			connectToHost();
			setupStreams();
			whileJoined();
		}catch(EOFException eofException){
			System.out.println("connection was terminated");
		}catch(IOException ioException){
			ioException.printStackTrace();
		}finally{
			closeStreams();
		}
	}
	
	private void connectToHost() throws IOException{
		System.out.println("Attempting to connect...");
		connection = new Socket(InetAddress.getByName(serverIP),6789);
		System.out.println("connected to: "+connection.getInetAddress().getHostName());
	}

	private void setupStreams() throws IOException{
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		System.out.println("io good to go");
	}	
	
	private void whileJoined() throws IOException{
		int x = 0;
		gameCode.setState(2);
		do{
			try{
				output.writeObject(player.getPosX());
				x = (int) input.readObject();
				System.out.println("from host to clinet, aka clients x = "+x);
			}catch(ClassNotFoundException classNotFoundException){
				System.out.println("not int sent");
			}
		}while(x<1000);
	}
	
	private void closeStreams(){
		System.out.println("closing streams and socket");
		gameCode.setState(1);
		try{
			output.close();
			input.close();
			connection.close();
			this.interrupt();
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
		
}