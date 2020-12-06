package jioSaavnApi.dataStructures;

import org.json.simple.JSONObject;

public class QuerySongInfo {
	private String id;
	private String title;
	private String album;
	private String description;
	private String primary_artists;
	private String singers;
	private String language;
	private String string_representation;
	
	public QuerySongInfo(JSONObject js) {
		this.id=(String)js.get("id");
		this.title=(String)js.get("title");
		this.album=(String)js.get("album");
		this.description=(String)js.get("description");
		JSONObject json = (JSONObject)js.get("more_info");
		this.language=(String)json.get("language");
		this.primary_artists=(String)json.get("primary_artists");
		this.singers=(String)json.get("singers");
		
		this.string_representation="id = "+this.id+"\n";
		this.string_representation+="title = "+this.title+"\n";
		this.string_representation+="album = "+this.album+"\n";
		this.string_representation+="description = "+this.description+"\n";
		this.string_representation+="primary_artists = "+this.primary_artists+"\n";
		this.string_representation+="singers = "+this.singers+"\n";
		this.string_representation+="language = "+this.language+"\n";
	}
	
	public String getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getAlbum() {
		return album;
	}
	public String getDescription() {
		return description;
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
	public String toString() {
		return string_representation;
	}
}
