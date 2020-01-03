package UI;

import java.util.HashMap;
import java.util.Map;

import View.Command;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class mainFX extends Application {

	private Map<String, Command> commands;
	
	public mainFX() {
		commands=new HashMap<>();
	}
	
	public void addCommand(Command c){ 
		 commands.put(c.getKey(),c);
	 }
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("intro.fxml"));
			primaryStage.setTitle("Main menu");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
