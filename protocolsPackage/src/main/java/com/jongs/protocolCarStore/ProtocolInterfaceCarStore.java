package com.jongs.protocolCarStore;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ProtocolInterfaceCarStore extends Remote {
    public String login(String login) throws RemoteException;
}
