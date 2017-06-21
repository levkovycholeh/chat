package client;

import structure.MessengerInterface;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import structure.GroupChatInterface;

public class Messenger extends UnicastRemoteObject implements MessengerInterface {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private GroupChatInterface server;
	
	public Messenger(String u, GroupChatInterface s) throws RemoteException  {
		username = u;
		server = s;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void tell(String s) {
		System.out.println(s);
	}
	
	public static void main(String[] args){
		
		if(System.getSecurityManager() == null){
			System.setSecurityManager(new SecurityManager());
		}
		
		try {
			String name = "Messenger";
			Registry registry = LocateRegistry.getRegistry(args[0]);
			GroupChatInterface gci = (GroupChatInterface) registry.lookup(name);
			
			Scanner scanner = new Scanner(System.in);
		    System.out.println("[System] Client Messenger is running");
	        System.out.println("Enter a username to login and press Enter:");
	        String username = scanner.nextLine();
	        MessengerInterface m = new Messenger(username, gci);
		    gci.login(m);
		    gci.sendToAll("Just Connected", m);	
		    for(;;){  	
		    	String aa = scanner.nextLine();
				gci.sendToAll(aa,m);				    		  				  				    	  
	    	}
		} catch (Exception e){
			System.err.println("Hello Client Exception: ");
			e.printStackTrace();
		}
	}
}
