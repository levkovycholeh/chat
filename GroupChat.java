package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Enumeration;
import java.util.Hashtable;
import structure.MessengerInterface;
import structure.GroupChatInterface;

public class GroupChat implements  GroupChatInterface {

	private Hashtable<String, MessengerInterface> l = new Hashtable<String, MessengerInterface>();
	
	public GroupChat() {
		super();
	};
	
	public boolean login(MessengerInterface m) throws RemoteException{
		l.put(m.getUsername(), m);
		m.tell("[Server] Welcome Client " + m.getUsername());
		return true;	
	}
	
	public void sendToAll(String s, MessengerInterface from) throws RemoteException{
		System.out.println("\n[" + from.getUsername() + "] " + s);
		Enumeration<String> usernames = l.keys();
		
		while(usernames.hasMoreElements()){
			String user = usernames.nextElement();
			MessengerInterface m = l.get(user);
			
			if(user.equals(from.getUsername())) {
				continue;
			}
			
			try{
				m.tell("\n[" + from.getUsername() + "] " + s);
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public MessengerInterface getMessenger(String username) throws RemoteException{
		MessengerInterface m = l.get(username);
		return m;
	}
	
	public static void main(String[] args){
		
		if(System.getSecurityManager() == null){
			System.setSecurityManager(new SecurityManager());
		}
		
		try{
			String name = "Messenger";
			GroupChatInterface msg = new GroupChat();
			GroupChatInterface stub = (GroupChatInterface) UnicastRemoteObject.exportObject(msg, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind(name, stub);
			System.out.println("[System] Chat server is ready.");
		} catch (Exception e){
			System.err.println("Chat server failed: ");
			e.printStackTrace();
		}
	}
}
