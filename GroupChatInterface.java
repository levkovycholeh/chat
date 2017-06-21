package structure;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GroupChatInterface extends Remote {
	
	public boolean login(MessengerInterface m) throws RemoteException;
	public void sendToAll(String s, MessengerInterface m) throws RemoteException;
	public MessengerInterface getMessenger(String username) throws RemoteException;
}
