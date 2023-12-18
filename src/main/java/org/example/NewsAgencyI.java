package org.example;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface NewsAgencyI extends Remote {
    void addNewsCategory(int categoryId,String categoryName) throws RemoteException;
    void deleteNewsCategory(int categoryId) throws RemoteException;
    void addNews(int categoryId, News news) throws RemoteException;
    void deleteNews(int categoryId, int newsId) throws RemoteException;
    void updateNews(int categoryId, int newsId, News news) throws RemoteException;

    void addNews(int categoryId, int newsId, String text) throws RemoteException;

    void deleteNews(int newsId) throws RemoteException;

    void updateNews(int newsId, int categoryId) throws RemoteException;

    int countNewsInCategory(int categoryId) throws RemoteException;
    News searchNewsByTitle(String title) throws RemoteException;

    News searchNewsByText(String newsTitle) throws RemoteException;

    News[] getAllNewsInCategory(int categoryId) throws RemoteException;
    NewsCategory[] getAllNewsCategories() throws RemoteException;
    void stop() throws RemoteException;
}
