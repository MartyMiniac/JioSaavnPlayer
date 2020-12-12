package test;

import java.io.IOException;

import httpRequests.HttpGet;

public class testMain2 {
	public static void main(String args[]) throws IOException {
		HttpGet h = new HttpGet("https://www.jiosaavn.com/api.php?__call=reco.getreco&api_version=4&_format=json&_marker=0&ctx=web6dot0&pid=gy17KLcd");
		System.out.println(h.get());
		System.out.println(h.getResponse());
	}
}
