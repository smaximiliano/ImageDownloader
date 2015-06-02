package com.downloader.image;

import java.io.IOException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException {
		GetItemNames getItemNames;
		DownloadImages downloadImages;
		List<String> itemNames;
		

		getItemNames = new GetItemNames(
				"https://global.api.pvp.net/api/lol/static-data/las/v1.2/item?itemListData=image&api_key=1f8cda40-b834-49e7-919c-cba942b6cb6f");
		itemNames = getItemNames.execute();
		
		List<String> completeUrls = getItemNames.getImgItemUrls(itemNames);
		
		downloadImages = new DownloadImages(completeUrls);
		downloadImages.execute();
		System.out.println("Images downloaded correctly...");
	}
}
