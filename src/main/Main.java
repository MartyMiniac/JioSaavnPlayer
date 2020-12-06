package main;

import java.io.IOException;
import java.util.Scanner;

import jioSaavnApi.Song;
import jioSaavnApi.SongQuery;
import jioSaavnApi.dataStructures.QuerySongInfo;
import jioSaavnApi.dataStructures.SongInfo;

public class Main {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the Song you are Searching for : ");
		String queryString=in.nextLine();
		
		SongQuery sq = new SongQuery(queryString);
		QuerySongInfo[] qsi = sq.getQueryList();
		printList(qsi);
		int val=(in.nextInt()-1);
		
		if(val==-1) {
			System.out.println("Exitting Program");
			System.exit(0);
		}
		if(val<-1 || val>=qsi.length) {
			System.out.println("Invalid Option Selection");
			System.out.println("Exitting Program");
			System.exit(0);
		}
		
		Song s = new Song(qsi[val]);
		SongInfo si = s.getSongInfo();
		System.out.println(si);
		
		try {
			Process pro = Runtime.getRuntime().exec("vlc "+si.getUrl());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void printList(QuerySongInfo[] arr) {
		System.out.println("Enter the Song Number you want to here : ");
		int i=1;
		for(QuerySongInfo s:arr) {
			System.out.println((i++)+":\t"+s.getTitle()+" ("+s.getAlbum()+")");
		}
		System.out.println("For None of The Above Press 0");
	}
}
