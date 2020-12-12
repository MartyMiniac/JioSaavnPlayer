package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import jioSaavnApi.Song;
import jioSaavnApi.SongQuery;
import jioSaavnApi.SongRecommendation;
import jioSaavnApi.dataStructures.QuerySongInfo;
import jioSaavnApi.dataStructures.SongInfo;

public class testMain {
	public static Scanner in = new Scanner(System.in);
	public static ArrayList<SongInfo> al = new ArrayList<SongInfo>();
	
	public static void main(String args[]) {
		while(true) {
			songAdditionProcess();
			System.out.println("Enter 0 to add more Songs Else the added songs will be played");
			int ch=in.nextInt();
			in.nextLine();
			if(ch!=0) {
				break;
			}
		}
		
		if(al.size()==0) {
			System.out.println("Playlist Empty\nExiting Program");
			in.close();
			System.exit(0);
		}
		
		System.out.println("\n\n\nSongs in the PlayList");
		int count=1;
		for(SongInfo i:al) {
			System.out.println((count++)+":\t"+i.getSong()+" of Album "+i.getAlbum()+" [Artist : "+i.getPrimary_artists()+"]");
		}
		
		try {
			Runtime.getRuntime().exec("vlc "+listToString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		in.close();
	}
	
	public static void songAdditionProcess() {
		System.out.print("Enter the Song you are Searching for : ");
		String queryString=in.nextLine();
		
		SongQuery sq = new SongQuery(queryString);
		QuerySongInfo[] qsi = sq.getQueryList();
		printList(qsi);
		int val=(in.nextInt()-1);
		
		if(val<=-1 || val>=qsi.length) {
			System.out.println("Invalid Option Selection");
			return;
		}
		Song s;
		try {
			s = new Song(qsi[val]);
		}
		catch(NullPointerException e) {
			System.err.println("Error : media_preview_url not found for the Choosen Song");
			return;
		}
		SongInfo si = s.getSongInfo();
		al.add(si);
		SongRecommendation ar = new SongRecommendation(si.getId());
		ArrayList<SongInfo> siarr = ar.getSongList();
		System.out.println("\n\nRecommendations : ");
		int count=1;
		for(SongInfo i : siarr) {
			System.out.println((count++)+":\t"+i.getSong()+" of Album "+i.getAlbum()+" [Artist : "+i.getPrimary_artists()+"]");
		}
		//Clears the InputBuffer
		in.nextLine();
		boolean k=true;
		while(k) {
			System.out.println("Enter y to add and n to not add");
			String inp=in.nextLine();
			switch(inp) {
			case "y":
				System.out.println("Recommendations Added to The Playlist");
				for(SongInfo i : siarr) {
					al.add(i);
				}
				k=false;
				break;
			case "n":
				System.out.println("Recommendations Not Added to The Playlist");
				k=false;
				break;
			default:
				System.out.println("Invalid Option Selection");
			}
		}
	}
	
	public static String listToString() {
		String s="";
		for(SongInfo i:al) {
			s+=i.getUrl()+" ";
		}
		return s;
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
