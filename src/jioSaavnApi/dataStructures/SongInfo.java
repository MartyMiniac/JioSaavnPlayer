package jioSaavnApi.dataStructures;

import org.json.simple.JSONObject;

import jioSaavnApi.SongLyrics;

public class SongInfo {
	private String id;
	private String song;
	private String album;
	private String year;
	private String primary_artists;
	private String singers;
	private String language;
	private boolean highres;	//320kbps playback
	private boolean has_lyrics;
	private String lyrics;
	private String url;
	private String release_date;
	private int duration;
	private String string_representation;
	
	public SongInfo(JSONObject js) {
		this.id=(String)js.get("id");
		this.song=(String)js.get("song");
		this.album=(String)js.get("album");
		this.year=(String)js.get("year");
		this.primary_artists=(String)js.get("primary_artists");
		this.singers=(String)js.get("singers");
		this.language=(String)js.get("language");
		
		String tmp=(String)js.get("320kbps");
		this.highres=tmp.equals("true");
		
		tmp=(String)js.get("has_lyrics");
		this.has_lyrics=tmp.equals("true");
		
		tmp=(String)js.get("media_preview_url");
		tmp=tmp.replaceAll("preview","aac");
		if(this.highres) {
			tmp=tmp.replace("_96_p.mp4", "_320.mp4");
		}
		else {
			tmp=tmp.replace("_96_p.mp4", "_160.mp4");
		}
		this.url=tmp;
		
		this.release_date=(String)js.get("release_date");
		this.duration=Integer.parseInt((String)js.get("duration"));
		if(this.has_lyrics) {
			this.lyrics=getLyrics();
		}
		else {
			this.lyrics="";
		}
		
		this.string_representation="id = "+this.id+"\n";
		this.string_representation+="title = "+this.song+"\n";
		this.string_representation+="album = "+this.album+"\n";
		this.string_representation+="year = "+this.year+"\n";
		this.string_representation+="primary_artists = "+this.primary_artists+"\n";
		this.string_representation+="singers = "+this.singers+"\n";
		this.string_representation+="language = "+this.language+"\n";
		this.string_representation+="highres = "+this.highres+"\n";
		this.string_representation+="has_lyrics = "+this.has_lyrics+"\n";
		this.string_representation+="lyrics = "+this.lyrics+"\n";
		this.string_representation+="url = "+this.url+"\n";
		this.string_representation+="release_date = "+this.release_date+"\n";
		this.string_representation+="duration = "+this.duration+"\n";
	}

	private String getLyrics() {
		SongLyrics sl = new SongLyrics(id);
		return sl.getLyrics();
	}
	public String toString() {
		return string_representation;
	}

	public String getId() {
		return id;
	}

	public String getSong() {
		return song;
	}

	public String getAlbum() {
		return album;
	}

	public String getYear() {
		return year;
	}

	public String getPrimary_artists() {
		return primary_artists;
	}

	public String getSingers() {
		return singers;
	}

	public String getLanguage() {
		return language;
	}

	public boolean isHighres() {
		return highres;
	}

	public boolean isHas_lyrics() {
		return has_lyrics;
	}

	public String getUrl() {
		return url;
	}

	public String getRelease_date() {
		return release_date;
	}

	public int getDuration() {
		return duration;
	}

	public String getString_representation() {
		return string_representation;
	}

	
}
