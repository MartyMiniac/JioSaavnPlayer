package jioSaavnApi;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import httpRequests.HttpGet;
import jioSaavnApi.dataStructures.SongInfo;
import jioSaavnApi.dataStructures.SongRecommendationInfo;

public class SongRecommendation {
	private final String url="https://www.jiosaavn.com/api.php?__call=reco.getreco&api_version=4&_format=json&_marker=0&ctx=web6dot0&pid=";
	private SongRecommendationInfo[] arr;
	
	public SongRecommendation(String id) {
		String finalQuery=new String(url+id);
		try {
			HttpGet ob = new HttpGet(finalQuery);
			if(ob.get()==200) {
				JSONParser parser = new JSONParser();  
				JSONArray json = (JSONArray) parser.parse(ob.getResponse());
				//json=(JSONObject)(((JSONObject)json.get("songs")).get("data"));
				parseSongs(json);
			}			
		} catch (IOException e) {
			System.out.println("Error : Unable to Search the Recommendation");
		} catch (ParseException e) {
			System.err.println("Error : Internal Error");
		}		

	}
	
	private void parseSongs(JSONArray js) {
		this.arr = new SongRecommendationInfo[js.size()];
		for(int i=0; i<arr.length; i++) {
			arr[i]= new SongRecommendationInfo((JSONObject)js.get(i));
		}
	}
	
	public SongRecommendationInfo[] getSongRecommendationList() {
		return arr;
	}
	
	public ArrayList<SongInfo> getSongList() {
		ArrayList<SongInfo> al = new ArrayList<SongInfo>();
		for(SongRecommendationInfo s :arr) {
			try {
				al.add((new Song(s.getId())).getSongInfo());				
			}
			catch(NullPointerException e) {
				System.err.println("Error : media_preview_url not found for id="+s.getId());
			}
		}
		return al;
	}
}
