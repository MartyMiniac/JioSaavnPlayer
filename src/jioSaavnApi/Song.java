package jioSaavnApi;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import httpRequests.HttpGet;
import jioSaavnApi.dataStructures.QuerySongInfo;
import jioSaavnApi.dataStructures.SongInfo;

public class Song {
	private final String getSongUrl="https://www.jiosaavn.com/api.php?__call=song.getDetails&cc=in&_marker=0%3F_marker%3D0&_format=json&pids=";
	SongInfo song=null;
	
	public Song(String id) {
		init(id);
	}
	
	public Song(QuerySongInfo qsi) {
		init(qsi.getId());
	}
	
	private void init(String id) {
		String finalSongUrl=new String(getSongUrl+id);		
		try {
			HttpGet ob = new HttpGet(finalSongUrl);
			if(ob.get()==200) {
				JSONParser parser = new JSONParser();  
				JSONObject json = (JSONObject) parser.parse(ob.getResponse());
				json=(JSONObject)json.get(id);
				song=new SongInfo(json);
			}			
		} catch (IOException e) {
			System.out.println("Error : Unable to Search the Song");
		} catch (ParseException e) {
			System.err.println("Error : Internal Error");
		}		
	}
	
	public SongInfo getSongInfo() {
		return song;
	}
}
