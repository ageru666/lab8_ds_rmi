package org.example;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class NewsAgencyImpl extends UnicastRemoteObject implements NewsAgencyI {
    private final NewsDAO newsDAO;

    public NewsAgencyImpl() throws RemoteException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        newsDAO = new NewsDAO();
    }

    @Override
    public void addNewsCategory(int categoryId,String categoryName) throws RemoteException {
        synchronized (newsDAO) {
            newsDAO.addNewsCategory(categoryId,categoryName);
        }
    }

    @Override
    public void deleteNewsCategory(int categoryId) throws RemoteException {
        synchronized (newsDAO) {
            newsDAO.deleteNewsCategoryById(categoryId);
        }
    }

    @Override
    public void addNews(int categoryId, News news) throws RemoteException {

    }

    @Override
    public void deleteNews(int categoryId, int newsId) throws RemoteException {

    }

    @Override
    public void updateNews(int categoryId, int newsId, News news) throws RemoteException {

    }

    @Override
    public void addNews(int categoryId, int newsId, String text) throws RemoteException {
        synchronized (newsDAO) {
            newsDAO.addNews(categoryId, newsId, text);
        }
    }

    @Override
    public void deleteNews(int newsId) throws RemoteException {
        synchronized (newsDAO) {
            newsDAO.deleteNews(newsId);
        }
    }

    @Override
    public void updateNews(int newsId, int categoryId) throws RemoteException {
        synchronized (newsDAO) {
            newsDAO.updateNews(newsId, categoryId);
        }
    }

    @Override
    public int countNewsInCategory(int categoryId) {
        synchronized (newsDAO) {
            return newsDAO.countNewsInCategory(categoryId);
        }
    }

    @Override
    public News searchNewsByTitle(String title) throws RemoteException {
        return null;
    }

    @Override
    public News searchNewsByText(String newsTitle) throws RemoteException {
        synchronized (newsDAO) {
            return newsDAO.searchNewsByText(newsTitle);
        }
    }

    @Override
    public News[] getAllNewsInCategory(int categoryId) throws RemoteException {
        synchronized (newsDAO) {
            ArrayList<News> newsList = newsDAO.getAllNewsInCategory(categoryId);
            return newsList.toArray(new News[0]);
        }
    }

    @Override
    public NewsCategory[] getAllNewsCategories() throws RemoteException {
        synchronized (newsDAO) {
            ArrayList<NewsCategory> categories = newsDAO.getAllCategories();
            return categories.toArray(new NewsCategory[0]);
        }
    }

    @Override
    public void stop() throws RemoteException {
        System.exit(0);
    }
}
