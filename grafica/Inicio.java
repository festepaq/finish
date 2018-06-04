package grafica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import grafica.MyButton;
import grafica.MyLabel;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logica.ColorPieza;
import logica.Juego;

public class Inicio extends Pane {
	private Juego juego;
	private int cantJugadores;
	private ColorPieza colorElegido;
	
	
	public Inicio(){
		super();
		this.setPrefSize(700, 800);
		
		Label titulo = new MyLabel("",100,50,500,100);
		titulo.setAlignment(Pos.TOP_CENTER);
		titulo.setId("labelChess");


		Button buttonDosJugadores = new MyButton("Dos Jugadores",200,320,300,100);
		buttonDosJugadores.getStyleClass().add("roundedButton");
		buttonDosJugadores.setOnAction(e -> ((Stage)(((Node) e.getSource()).getScene().getWindow())).setScene(new Scene(new PantallaJuego(2,ColorPieza.BLANCO))));

                Button buttonIngresar = new MyButton("Ingresar",200,200,300,100);
		buttonIngresar.getStyleClass().add("roundedButton");
		buttonIngresar.setOnAction(e -> ((Stage)(((Node) e.getSource()).getScene().getWindow())).setScene(new Scene(new PantallaJuego(2,ColorPieza.BLANCO))));

                
                Button buttonRegistrarse = new MyButton("Registrar Usuario",200,440,300,100);
		buttonRegistrarse.getStyleClass().add("roundedButton");
		buttonRegistrarse.setOnAction(e -> ((Stage)(((Node) e.getSource()).getScene().getWindow())).setScene(new Scene(new RegistrarUsuario())));

        
		Button buttonSalir = new MyButton("Salir",200,560,300,100);
		buttonSalir.getStyleClass().add("roundedButton");
		buttonSalir.setOnAction(e-> ((Stage)(((Node) e.getSource()).getScene().getWindow())).close());
		
		this.getChildren().addAll(titulo,buttonIngresar,buttonDosJugadores,buttonRegistrarse,buttonSalir);
		this.getStylesheets().add(getClass().getResource("../assets/application.css").toExternalForm());
		
	}
	
//
}
