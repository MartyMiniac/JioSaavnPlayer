package jioSaavnApi;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import httpRequests.HttpGet;
import jioSaavnApi.dataStructures.SongInfo;

public class Album {
	private final String url="https://www.jiosaavn.com/api.php?__call=content.getAlbumDetails&_format=json&cc=in&_marker=0%3F_marker%3D0&albumid=";
	private SongInfo[] arr;
	
	public Album(String id) {
		String finalQuery=new String(url+id);		
		try {
			HttpGet ob = new HttpGet(finalQuery);
			if(ob.get()==200) {
				JSONParser parser = new JSONParser();  
				JSONObject json = (JSONObject) parser.parse(ob.getResponse());
				JSONArray jsonarr=(JSONArray)json.get("songs");
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
		this.arr = new SongInfo[js.size()];
		for(int i=0; i<arr.length; i++) {
			Song s = new Song((String)((JSONObject)js.get(i)).get("id"));
			arr[i]= s.getSongInfo();
		}
	}
	
	public SongInfo[] getSongInfoList() {
		return arr;
	}

}
