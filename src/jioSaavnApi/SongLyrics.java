package jioSaavnApi;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import httpRequests.HttpGet;

public class SongLyrics {
	private String id;
	private final String url="https://www.jiosaavn.com/api.php?__call=lyrics.getLyrics&ctx=web6dot0&api_version=4&_format=json&_marker=0%3F_marker%3D0&lyrics_id=";
	private String lyrics;
	
	public SongLyrics(String id) {
		this.id=id;
		startrequest();
		parseLyrics();
	}
	
	private void startrequest() {
		String tmp=url+id;
		try {
			HttpGet h = new HttpGet(tmp);
			if(h.get()==200) {
				JSONParser parser = new JSONParser();  
				JSONObject json = (JSONObject) parser.parse(h.getResponse());
				this.lyrics=(String)json.get("lyrics");
			}
			else {
				this.lyrics=null;
				System.err.println("Error in Getting Lyrics");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	private void parseLyrics() {
		this.lyrics=this.lyrics.replaceAll("<br>", "\n");
	}
	
	public String getLyrics() {
		return this.lyrics;
	}
	public String toString() {
		return lyrics;
	}
}
