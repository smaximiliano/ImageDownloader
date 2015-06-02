package com.downloader.image;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GetItemNames {
	private String url;

	public GetItemNames(String URL) {
		url = URL;
	}

	public List<String> execute() throws IOException {
		URL url = new URL(this.url);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		if (conn.getResponseCode() != 200) {
			throw new IOException(conn.getResponseMessage());
		}

		BufferedReader rd = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();

		conn.disconnect();

		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(sb.toString());
		JsonObject jo = je.getAsJsonObject();

		je = jo.get("data");
		jo = je.getAsJsonObject();

		/*
		 * entry is data.1001entry is data.1054entry is data.etc...
		 */

		List<String> imagenes = new ArrayList<String>();
		for (Map.Entry<String, JsonElement> entry : jo.entrySet()) {
			imagenes.add(entry.getValue().getAsJsonObject().get("image")
					.getAsJsonObject().get("full").getAsString());
		}
		return imagenes;
	}

	public List<String> getImgItemUrls(List<String> itemNames) {
		List<String> urls = new ArrayList<String>();
		String urlImgItem = "http://ddragon.leagueoflegends.com/cdn/5.2.1/img/item/";

		for (String item : itemNames) {
			urls.add(urlImgItem + item);
		}
		return urls;
	}

}
