import java.lang.Thread;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;

import java.io.IOException;
import java.io.EOFException;


public class Host extends Thread{
	
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;
	
	private GameCode gameCode;
	private Player player;
	
	public Host(){
		
	}
	public void getNeededVars(GameCode gc, Player pl){
		gameCode = gc;
		player = pl;
	}
	public void run(){
		try{
			server = new ServerSocket(6789, 2);
			while(true){
				try{
					waitForConnection();
					setupStreams();
					whileHostActive();
				}catch(EOFException eofException){
					System.out.println("Connection ended");
				}finally{
					closeStreams();
					System.out.println("PEACE, IM OUT");
				}
			}
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	
	private void waitForConnection() throws IOException{
		System.out.println("waiting for someone to connect");
		connection = server.accept(); // loop inside?
		System.out.println("connected to "+connection.getInetAddress().getHostName());
	}
	private void setupStreams() throws IOException{
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		System.out.println("Streams are setup");
	}
	private void whileHostActive() throws IOException{
		System.out.println("you're now connected");
		gameCode.setState(2);
		int x = 0;
		do{
			try{
				x = (int) input.readObject();
				System.out.println("From guest to host, aka guest x = "+x);
				output.writeObject(player.getPosX()); // or here
				output.flush(); // mb here
			}catch(ClassNotFoundException classNotFoundException){
				System.out.println("not int sent");
			}catch(IOException ioException){
				System.out.println("cant send X!");
				break;
			}
		}while(x<1000);
	}
	
	private void closeStreams(){
		System.out.println("closing streams");
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