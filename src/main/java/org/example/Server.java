package org.example;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public class Server {
    public static void main(String[] args) throws RemoteException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        NewsAgencyImpl newsAgencyImpl = new NewsAgencyImpl();
        Registry registry = LocateRegistry.createRegistry(123);
        registry.rebind("news_agency", newsAgencyImpl);
        System.out.println("Server started!");
    }
}
