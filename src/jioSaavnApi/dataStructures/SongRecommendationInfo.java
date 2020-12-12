package jioSaavnApi.dataStructures;

import org.json.simple.JSONObject;

public class SongRecommendationInfo {
	private String id;
	
	public SongRecommendationInfo(JSONObject js) {
		this.id=(String)js.get("id");
	}
	public String getId() {
		return id;
	}
}
