package com.downloader.image;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

public class DownloadImages {

	List<String> urls;

	public DownloadImages(List<String> urls) {
		this.urls = urls;
	}

	public void execute() throws IOException {
		URL url;
		for (String urlImg : urls) {

			url = new URL(urlImg);
			String fileName = url.getFile();
			String destName = fileName.substring(fileName.lastIndexOf("/") + 1);

			InputStream is = null;
			try {
				is = url.openStream();
			
			
			OutputStream os = new FileOutputStream(destName);

			System.out.println(destName);
			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}

			is.close();
			os.close();
			
			} catch (FileNotFoundException fnfe) {
				System.out.println("File not found "+urlImg);
			}
		}

	}
}
