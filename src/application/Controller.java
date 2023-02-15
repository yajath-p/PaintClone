package application;

import java.io.File;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

//Responsible with handling GUI interactions
public class Controller {
	
	@FXML
	private Canvas myCanvas;
	@FXML
	private Slider brushSize;
	@FXML
	private ColorPicker colorPicker;
	@FXML
	private ToggleButton tool;
	@FXML
	private TextField textfield;
	@FXML 
	private TextField width;
	@FXML 
	private TextField length;
	@FXML
	private AnchorPane border;

	//Changes button name to corresponding tool selected
	public void nameChange(ActionEvent e) {
		if(tool.getText()=="Eraser") {
			tool.setText("Pen");
		} else {
			tool.setText("Eraser");	
		}
	}

	//Does the drawing and initial border size
	public void initialize() {
		GraphicsContext g = myCanvas.getGraphicsContext2D();

		myCanvas.setOnMouseDragged(e -> {
			double size = brushSize.getValue()/4;
			double x = e.getX() - size/2;
			double y = e.getY() - size/2;
			if(tool.getText()=="Eraser") {
				g.clearRect(x, y, size, size);
			} else {
				g.setFill(colorPicker.getValue());
				g.fillRect(x,y,size+1,size+1);
			}
		});
		//Sets starting canvas size
		border.setMaxWidth(400);
		border.setMinWidth(400);
		border.setPrefWidth(400);
		border.setMaxHeight(400);
		border.setMinHeight(400);
		border.setPrefHeight(400);
	}
	
	//Changes canvas width based on user input
	public void canvasSizeWidth(ActionEvent e) {
		myCanvas.setWidth(Double.valueOf(width.getText()));
		border.setMaxWidth(Integer.valueOf(width.getText()));
		border.setMinWidth(Integer.valueOf(width.getText()));
		border.setPrefWidth(Integer.valueOf(width.getText()));
	}

	//Changes canvas length based on user input
	public void canvasSizeLength(ActionEvent e) {
		myCanvas.setHeight(Double.valueOf(length.getText()));
		border.setMaxHeight(Integer.valueOf(length.getText()));
		border.setMinHeight(Integer.valueOf(length.getText()));
		border.setPrefHeight(Integer.valueOf(length.getText()));
	}

	//Clears canvas when new button pressed
	public void onNew() {
		myCanvas.getGraphicsContext2D().clearRect(0, 0, myCanvas.getWidth(), myCanvas.getHeight());
	}

	//Saves canvas drawing with custom name as png file
	public void onSave() { 
		try {
			Image snapshot = myCanvas.snapshot(null, null);

			ImageIO.write(SwingFXUtils.fromFXImage(snapshot,null), "png", new File(textfield.getText() + ".png"));
		} catch (Exception e) {
			System.out.println("Failed saving image" + e);
		}
	}

	//Exits application
	public void onQuit() {
		System.exit(0);
	}

}
