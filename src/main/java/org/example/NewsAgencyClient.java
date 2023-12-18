package org.example;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class NewsAgencyClient {
    private static final Scanner scanner = new Scanner(System.in);

    public static void showMenu() {
        System.out.println("1. Add a new news category");
        System.out.println("2. Delete a news category");
        System.out.println("3. Add news to a category");
        System.out.println("4. Delete news");
        System.out.println("5. Update news");
        System.out.println("6. Count total number of news in a category");
        System.out.println("7. Search for news by title");
        System.out.println("8. Get all news in a category");
        System.out.println("9. Get all news categories");
        System.out.println("10. Exit");
    }

    public static void main(String[] args) throws NotBoundException, RemoteException, MalformedURLException {
        try {
            String url = "//localhost:123/news_agency";
            NewsAgencyI newsAgency = (NewsAgencyI) Naming.lookup(url);
            System.out.println("RMI object found");

            String choice = "";
            while (true) {
                showMenu();
                choice = scanner.nextLine();

                switch (choice) {
                    case "1" -> {
                        System.out.println("Enter news category ID: ");
                        int categoryId = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter news category name: ");
                        String newCategoryName = scanner.nextLine();
                        newsAgency.addNewsCategory(categoryId ,newCategoryName);
                    }
                    case "2" -> {
                        System.out.println("Enter news category ID: ");
                        int categoryId = Integer.parseInt(scanner.nextLine());
                        newsAgency.deleteNewsCategory(categoryId);
                    }
                    case "3" -> {
                        System.out.println("Enter news category ID: ");
                        int categoryId = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter news ID: ");
                        int newsId = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter news title: ");
                        String newsTitle = scanner.nextLine();
                        News news = new News(newsId, newsTitle);
                        newsAgency.addNews(categoryId, news);
                    }
                    case "4" -> {
                        System.out.println("Enter news category ID: ");
                        int categoryId = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter news ID: ");
                        int newsId = Integer.parseInt(scanner.nextLine());
                        newsAgency.deleteNews(categoryId, newsId);
                    }
                    case "5" -> {
                        System.out.println("Enter news category ID: ");
                        int categoryId = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter news ID: ");
                        int newsId = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter new news title: ");
                        String newNewsTitle = scanner.nextLine();
                        News updatedNews = new News(newsId, newNewsTitle);
                        newsAgency.updateNews(categoryId, newsId, updatedNews);
                    }
                    case "6" -> {
                        System.out.println("Enter news category ID: ");
                        int categoryId = Integer.parseInt(scanner.nextLine());
                        System.out.println("Total number of news in category " + categoryId + " is " + newsAgency.countNewsInCategory(categoryId));
                    }
                    case "7" -> {
                        System.out.println("Enter news title: ");
                        String newsTitle = scanner.nextLine();
                        System.out.println(newsAgency.searchNewsByTitle(newsTitle));
                    }
                    case "8" -> {
                        System.out.println("Enter news category ID: ");
                        int categoryId = Integer.parseInt(scanner.nextLine());
                        News[] newsArray = newsAgency.getAllNewsInCategory(categoryId);
                        for (News news : newsArray) {
                            System.out.println(news);
                        }
                    }
                    case "9" -> {
                        NewsCategory[] categories = newsAgency.getAllNewsCategories();
                        for (NewsCategory category : categories) {
                            System.out.println(category);
                        }
                    }
                    case "10" -> {
                        System.out.println("Exiting...");
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid choice.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
