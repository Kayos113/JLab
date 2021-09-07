module JLab {
	requires javafx.controls;
	requires java.desktop;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
	
	exports controller to javafx.fxml;
	opens controller to javafx.fxml;
}
