package data.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import data.ArticleDAO;
import transfer.Article;



public class SqlArticleDAO extends SqlDaoBase implements ArticleDAO {
  
	private static final String findAllQuery = "SELECT ID,NAME,PRICE,quantity FROM Articles WHERE true";
	private static final String findQuery = "SELECT * FROM Articles WHERE ID=?";
	private static final String updateQuery = "UPDATE Articles SET name=?, price=?, quantity=? WHERE ID=?";
	private static final String insertQuery = "INSERT INTO Articles (name,price,quantity) VALUES(?, ?, ?)";
	private static final String deleteQuery = "DELETE FROM Articles WHERE ID=?";
	
	
	@Override
    public Article get(long id) {
		Article a = null;
		try
		{			
			PreparedStatement statement = getConnection().prepareStatement(findQuery);
			statement.setLong(1, id );
		
			ResultSet results = statement.executeQuery();
			if (results.next()) { 
				a = new Article(results.getInt(1), results.getString(2),results.getFloat(3),results.getInt(4));	
			} 
	
			statement.close();	
			results.close();			
	    } 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return a;
    }
     
    @Override
    public List<Article> getAll() {

    	List<Article> articles = null;
		try
		{			
			PreparedStatement statement = getConnection().prepareStatement(findAllQuery);
			
			articles = new ArrayList<Article>();
			ResultSet results = statement.executeQuery();
			while (results.next()) { 
				articles.add(new Article(results.getInt(1),results.getString(2),results.getFloat(3),results.getInt(4)));				
			} 
			statement.close();
	
			results.close();
			
			return articles;
	    } 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;

    }
     
    @Override
    public void add(Article a) {
    	try
	    {
	      
	      PreparedStatement statement = getConnection().prepareStatement(insertQuery);
	      statement.setString(1, a.getName());
	      statement.setFloat(2, a.getPrice());
	      statement.setInt(3, a.getQuantity());  
	      statement.execute();
	      statement.close();
	  	
			
	    } 
		catch(Exception e)
		{
			e.printStackTrace();
		}
    	
    }
     
    @Override
    public void update(Article a) {
    	try
	    {
	      
	      PreparedStatement statement = getConnection().prepareStatement(updateQuery);
	      statement.setString(1, a.getName());
	      statement.setFloat(2, a.getPrice());
	      statement.setInt(3, a.getQuantity());
	      statement.setLong(4, a.getId());
	     
	      statement.executeUpdate();
	      statement.close();
	  	
			
	    } 
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
     
    @Override
    public void delete(Article a) {
    	try
	    {
	      
	      PreparedStatement statement = getConnection().prepareStatement(deleteQuery);

	      statement.setLong(1, a.getId());
	     
	      statement.execute();
	      statement.close();	  	
			
	    } 
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }



	
}
