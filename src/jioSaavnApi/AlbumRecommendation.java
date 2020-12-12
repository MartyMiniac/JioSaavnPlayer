package jioSaavnApi;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import httpRequests.HttpGet;
import jioSaavnApi.dataStructures.AlbumRecommendationInfo;
import jioSaavnApi.dataStructures.SongInfo;

public class AlbumRecommendation {
	private final String url="https://www.jiosaavn.com/api.php?__call=reco.getAlbumReco&api_version=4&_format=json&_marker=0&albumid=";
	private AlbumRecommendationInfo[] arr;
	
	public AlbumRecommendation(String id) {
		String finalQuery=new String(url+id);
		System.out.println(finalQuery);
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
		this.arr = new AlbumRecommendationInfo[js.size()];
		for(int i=0; i<arr.length; i++) {
			arr[i]= new AlbumRecommendationInfo((JSONObject)js.get(i));
		}
	}
	
	public AlbumRecommendationInfo[] getAlbumRecommendationList() {
		return arr;
	}
	
	public ArrayList<SongInfo> getSongList() {
		ArrayList<SongInfo> al = new ArrayList<SongInfo>();
		for(AlbumRecommendationInfo s :arr) {
			Album a = new Album(s.getId());
			for(SongInfo si : a.getSongInfoList()) {
				al.add(si);
			}
		}
		return al;
	}
}
