package jioSaavnApi.dataStructures;

import org.json.simple.JSONObject;

public class AlbumRecommendationInfo {
	private String id;
	
	public AlbumRecommendationInfo(JSONObject js) {
		this.id=(String)js.get("id");
	}
	public String getId() {
		return id;
	}
}
