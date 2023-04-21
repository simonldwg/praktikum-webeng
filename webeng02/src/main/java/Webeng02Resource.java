


import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestREST
 */
@WebServlet("/resource")
public class Webeng02Resource extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public class ResourceData 
	{
		@Override
		public String toString() {
			return key + ": " + value ;
		}
		
		public String key;
		public String value;
		
		
	}
	
	List<ResourceData> data = new ArrayList<ResourceData>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Webeng02Resource() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resp = "";
		
		String id = extractId(request);
		
		if(id != null)	// list single item
		{
			resp+= "list id: " + id + "\n";
			String finalId = id;
			Optional<ResourceData> d =  this.data.stream().filter(x -> x.key.equals(finalId)).findAny();
			if(d.isEmpty())
				resp+= "element not found \n";
			else
				resp+= d.get().toString();
		}
		else	// list all items
		{
			resp+="list all: " + data.size() + " elements \n---------------------------------\n";
			for(ResourceData d : data)
			{
				resp+=d.toString() + "\n---------------------------------\n";
			}
		}

		// List headers
		resp += "\n---- headers ----\n";

		// Get response header names
		final Enumeration<String> headerNames = request.getHeaderNames();

		while(headerNames.hasMoreElements()) {
			final String headerName = headerNames.nextElement();
			resp += headerName + ": " + request.getHeader(headerName) + "\n";
		}

		response.getWriter().append(resp);

		
	}
	
	protected String extractId(HttpServletRequest request)
	{
		String id = request.getParameter("id");
		if(id != null)
		{
			return id;
		}
		return null;		

	}

	protected ResourceData findById(String id) {
		Optional<ResourceData> data = this.data.stream().filter(x -> x.key.equals(id)).findAny();
		if(data.isEmpty()) {
			return null;
		}
		return data.get();
	}
	
	protected String addOrUpdate(String id,  ResourceData testData)
	{
		String res = "";
		ResourceData query = findById(id);
		if(query == null)
		{
			res+= "add id: " + id + "\n";
			

			data.add(testData);
		}
		else
		{
			res+= "update id: " + id + "\n";
			query.key = testData.key;
			query.value = testData.value;
			
		}
		res+=testData.toString();
		return res;
	}



	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resp = "";
		
		String id = extractId(request);;
		
		if(id != null)
		{
			String _key = request.getParameter("key"); 
			String _value = request.getParameter("value");
			resp+=_key+": " + _value + "\n";


			if(_key != null && _value != null) {
				resp += this.addOrUpdate(_key, new ResourceData() {{
					key = _key;
					value = _value;
				}});
			}

			
		}
		else
		{
			response.getWriter().append(resp);
			response.sendError(HttpServletResponse.SC_FORBIDDEN,"Could not create/update resource, no id given");	// FORBIDDEN, BAD_REQUEST
		}
		
		
		response.getWriter().append(resp);
	}





	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resp = "";
		
		String id = extractId(request);;
		
		if(id == null)
		{
			String _key = request.getParameter("key"); 
			String _value = request.getParameter("value");
			resp+=_key+": " + _value + "\n";

			if(_key != null && _value != null) {
				resp += this.addOrUpdate(_key, new ResourceData() {{
					key = _key;
					value = _value;
				}});
			}
			
		}
		else
		{
			response.getWriter().append(resp);
			response.sendError(HttpServletResponse.SC_FORBIDDEN,"Could not create/update resource, because id was given");	// FORBIDDEN, BAD_REQUEST
		}
		
		
		response.getWriter().append(resp);
	}
	
	
	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Allow", "POST, PUT, DELETE, GET");
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final String id = extractId(request);

		if(id == null) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN,"Could not delete resource, no id given");
			return;
		}

		final ResourceData query = findById(id);

		if(query == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "No element found with this id");
		} else {
			this.data.remove(query);
			response.getWriter().append("successfully removed element");
		}
		
	}


}
