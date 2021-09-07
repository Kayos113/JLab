package model;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

public class AudioQueue {

	private LinkedList<AudioClip> audioList;
	private int nElems;
	
	private static AudioQueue audioQueue;
	
	private AudioQueue() {
		audioList = new LinkedList<AudioClip>();
		nElems = 0;
	}
	
	public static AudioQueue getAudioQueue() {
		if(audioQueue==null) {
			audioQueue = new AudioQueue();
		}
		return audioQueue;
	}
	
	public void addClipToEnd(File f) {
		AudioClip newClip = new AudioClip(f);
		audioList.add(newClip);
		nElems++;
	}
	
	public void addClipAtIndex(File f, int i) {
		AudioClip newClip = new AudioClip(f);
		audioList.add(newClip);
		nElems++;
	}
	
	public void removeClipAtIndex(int i) {
		audioList.remove(i);
	}
		
	public void playClipAtIndex(int i) {
		audioList.get(i).start();
	}
	
	public ArrayList<String> getTitles() {
		ArrayList<String> returnable = new ArrayList<String>();
		for(int i=0; i<nElems; i++) {
			returnable.add(audioList.get(i).getTitle());
		}
		return returnable;
	}

	public int length() {
		return nElems;
	}
}
