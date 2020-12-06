package jioSaavnApi;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import httpRequests.HttpGet;
import jioSaavnApi.dataStructures.QuerySongInfo;

public class SongQuery {
	private final String queryUrl="https://www.jiosaavn.com/api.php?__call=autocomplete.get&_format=json&_marker=0&cc=in&includeMetaTags=1&query=";
	private QuerySongInfo[] arr;
	
	public SongQuery(String queryString) {
		queryString=queryString.replaceAll(" ", "%20");
		String finalQuery=new String(queryUrl+queryString);		
		try {
			HttpGet ob = new HttpGet(finalQuery);
			if(ob.get()==200) {
				JSONParser parser = new JSONParser();  
				JSONObject json = (JSONObject) parser.parse(ob.getResponse());
				json=(JSONObject)json.get("songs");
				JSONArray jsonarr=(JSONArray)json.get("data");
				//json=(JSONObject)(((JSONObject)json.get("songs")).get("data"));
				parseSongs(jsonarr);
			}			
		} catch (IOException e) {
			System.out.println("Error : Unable to Search the Song");
		} catch (ParseException e) {
			System.err.println("Error : Internal Error");
		}		

	}
	
	private void parseSongs(JSONArray js) {
		this.arr = new QuerySongInfo[js.size()];
		for(int i=0; i<arr.length; i++) {
			arr[i]= new QuerySongInfo((JSONObject)js.get(i));
		}
	}
	
	public QuerySongInfo[] getQueryList() {
		return arr;
	}

}
