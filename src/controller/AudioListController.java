package controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.AudioQueue;

public class AudioListController implements Initializable {

	private AudioQueue audioQueue;
	
	@FXML
	private ListView<String> audioCueList;
	
	@FXML
	private ImageView playImageView, newCueImageView, deleteCueImageView, nextCueImageView, prevCueImageView;

	
	@FXML
	public void playButtonPressed() {
		//add a new Cue to the Queue
	//	int index = audioCueList.getFocusModel().getFocusedIndex();
		audioQueue.playClipAtIndex(index());
	//	audioCueList.getFocusModel().focus(++index);
		increment();
	}
	
	@FXML
	public void addCueButtonPressed() {
		//play selected Cue
		FileChooser fc = new FileChooser();
        fc.setTitle("My File Chooser");
        FileChooser.ExtensionFilter fileExtensions = 
        		  new FileChooser.ExtensionFilter(
        		    "Audio Files", "*.wav", "*.mp3");
        fc.getExtensionFilters().add(fileExtensions);
        
        Stage primaryStage = new Stage();
		File f = fc.showOpenDialog(primaryStage);
		audioQueue.addClipToEnd(f);
		
		populateCueList();
	}
	
	@FXML
	public void deleteCueButtonPressed() {
		int index = index();
		audioQueue.removeClipAtIndex(index);
	}
	
	@FXML
	public void nextCueButtonPressed() {
		increment();
	}
	
	@FXML
	public void prevCueButtonPressed() {
		decrement();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		audioQueue = Main.getAudioQueue();
		populateCueList();
		renderIcons();
	}
	
	public void populateCueList()
	{
		ArrayList<String> titles = audioQueue.getTitles();
		ObservableList<String> items = FXCollections.observableArrayList(titles);
		audioCueList.setItems(items);
		if(!items.isEmpty()) {
			//audioCueList.getFocusModel().focus(0);
			audioCueList.getSelectionModel().select(0);
		}
	}
	
	public void renderIcons() {
		try {
	        Image playIcon = new Image("file:\\Users\\Achi1\\eclipse-workspace\\JLab\\src\\controller\\images\\play.png");
	        Image addIcon = new Image("file:\\Users\\Achi1\\eclipse-workspace\\JLab\\src\\controller\\images\\add.png");
	        Image deleteIcon = new Image("file:\\Users\\Achi1\\eclipse-workspace\\JLab\\src\\controller\\images\\minus.png");
	        Image nextIcon = new Image("file:\\Users\\Achi1\\eclipse-workspace\\JLab\\src\\controller\\images\\down-arrow.png");
	        Image prevIcon = new Image("file:\\Users\\Achi1\\eclipse-workspace\\JLab\\src\\controller\\images\\up-arrow.png");
	        
	        playImageView.setImage(playIcon);
	        playImageView.setCache(true);
	        newCueImageView.setImage(addIcon);
	        newCueImageView.setCache(true);
	        deleteCueImageView.setImage(deleteIcon);
	        deleteCueImageView.setCache(true);
	        nextCueImageView.setImage(nextIcon);
	        nextCueImageView.setCache(true);
	        prevCueImageView.setImage(prevIcon);
	        prevCueImageView.setCache(true);
	    } catch (Exception e) {
	        System.out.println(e);;
	    }
	}
	
	private int index() {
		return audioCueList.getSelectionModel().getSelectedIndex();
	}
	
	private void increment() {
		if(!(index()+1>audioQueue.length()))
			audioCueList.getSelectionModel().select(index()+1);
	}
	
	private void decrement() {
		if(!(index()-1<0))
			audioCueList.getSelectionModel().select(index()-1);
	}
}
