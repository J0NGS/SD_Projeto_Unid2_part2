package com.jongs.protocol;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ProtocolInterface extends Remote {
    public String login(String login) throws RemoteException;
}
