package presentation.app;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import configuration.Configuration;
import data.ArticleDAO;
import data.DAOFactory;
import transfer.Article;

public class TestDao {

	public static void main(String[] args) {
		
		System.out.println("-----------------------------------------------");
		System.out.println("DaoTest.App");
		System.out.println("-----------------------------------------------");
		
		
		Configuration.setDataSourceType("ORM");
    	Configuration.setUsername("sa");
    	Configuration.setPassword("sa");
    	Configuration.setUrl("jdbc:h2:./h2/db");
    	Configuration.setDriver("org.h2.Driver");

    
		
        try
        {
        	Statement stmt = null;

        	Class.forName("org.h2.Driver");
        	Connection conn = null;        	
            try
            {
            	conn = DriverManager.getConnection(Configuration.getUrl(),Configuration.getUsername(),Configuration.getPassword());        	//
            	stmt = conn.createStatement();
            	stmt.execute("drop table if exists articles");
            	stmt.execute("create table articles (\r\n"
            			+ "  id                            bigint generated by default as identity not null,\r\n"
            			+ "  name                          varchar(255) not null,\r\n"
            			+ "  price                         float not null,\r\n"
            			+ "  quantity                		int not null,\r\n"
            			+ "  constraint pk_articles primary key (id)\r\n"
            			+ ")");
            }
            catch(SQLException e)
            {
            	System.out.println(e.getMessage());
            }
    		
    		ArticleDAO articleDAO = DAOFactory.getArticleDAO();
        	
            	
            	 articleDAO.add(new Article(0,"TestArtikel01",10,3));
                 
                 articleDAO.add(new Article(0,"TestArtikel02",20,5));
               
                 articleDAO.add(new Article(0,"TestArtikel03",30,7));
                 
                 articleDAO.add(new Article(0,"TestArtikel04",40,4));
                 
                 articleDAO.add(new Article(0,"TestArtikel05",50,2));
                 
                 articleDAO.add(new Article(0,"TestArtikel06",60,6));
           
        	       	
            System.out.println("\n\n-------------------------------------------------");
            System.out.println("Alle Artikel:");
            System.out.println("-------------------------------------------------");
    		
    		for (Article a : articleDAO.getAll()) {
    			System.out.println(a.getId());
    			System.out.println(a.getName());
    			System.out.println(a.getPrice());
    			System.out.println(a.getQuantity());
    			System.out.println();
    		}
    		System.out.println("-------------------------------------------------");
    		System.out.println("Artikel mit ID 3");
    		System.out.println("-------------------------------------------------");
    		Article a = articleDAO.get(3);
    		
    		System.out.println(a.getId());
    		System.out.println(a.getName());
    		System.out.println(a.getPrice());
    		System.out.println(a.getQuantity());
    		System.out.println();
    		
    		System.out.println("-------------------------------------------------");
    		System.out.println("Artikel mit ID 3 aktualisieren");
    		System.out.println("-------------------------------------------------");
    		a = articleDAO.get(3);
    		a.setName(a.getName()+"Update");
    		a.setQuantity(a.getQuantity() + 2);
    		a.setPrice(a.getPrice() + 1);
    		articleDAO.update(a);
    		a = articleDAO.get(3);
    		
    		System.out.println(a.getId());
    		System.out.println(a.getName());
    		System.out.println(a.getPrice());
    		System.out.println(a.getQuantity());
    		System.out.println();
    		System.out.println("-------------------------------------------------");
            
        }
        catch(Exception e)
        {
        	System.out.println(e.getMessage());
        }
	}

}