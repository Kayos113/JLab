package model;

import java.io.File;
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
	}
	
	public void addClipAtIndex(File f, int i) {
		AudioClip newClip = new AudioClip(f);
		audioList.add(newClip);
	}
	
	public void removeClipAtIndex(int i) {
		audioList.remove(i);
	}
	
	
	
}
