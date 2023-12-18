package io.github.leothawne.LTSleepNStorm.module;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.github.leothawne.LTSleepNStorm.api.HTTPAPI;

public final class AdModule {
	public static final ArrayList<JsonObject> loadAdList() {
		final String response1 = HTTPAPI.getData(DataModule.getAdURL());
		if(response1 != null) {
			final ArrayList<JsonObject> adList = new ArrayList<JsonObject>();
			final JsonParser parser = new JsonParser();
			final JsonObject remoteAdList = (JsonObject) parser.parse(response1);
			final JsonArray adArray = remoteAdList.getAsJsonArray("adlist");
			for(int i = 0; i < adArray.size(); i++){
				final JsonElement o = adArray.get(i);
				final String response2 = HTTPAPI.getData(o.getAsString());
				if(response2 != null) {
					final JsonObject remoteAd = (JsonObject) parser.parse(response2);
					if(remoteAd.get("active").getAsBoolean()) adList.add(remoteAd);
				}
			}
			return adList;
		}
		return null;
	}
}