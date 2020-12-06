package httpRequests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpGet {
	private final String USER_AGENT = "Mozilla/5.0";
	private String GET_URL = "";
	private HttpURLConnection con;
	private int responseCode;
	public String Text;
	
	public HttpGet(String url) throws IOException{
		this.GET_URL=url;
		URL obj = new URL(GET_URL);
		this.con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
	}
	
	public int get() throws IOException {
		this.responseCode = con.getResponseCode();
		return responseCode;
	}
	public String getResponse() {
		try {
			if (this.responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in;
				in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
	
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				
				this.Text=response.toString();
				return response.toString();
			} 
			else {
				System.out.println("GET request not worked");
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
}
