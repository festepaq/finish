/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafica;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Farid Estepa
 */
public class RegistrarUsuario extends Pane {

    public RegistrarUsuario() {

        this.setPrefSize(900, 800);
        this.getStylesheets().add(getClass().getResource("../assets/application.css").toExternalForm());

        Label titulo = new MyLabel("", 200, 20, 500, 100);
        titulo.setAlignment(Pos.TOP_CENTER);
        titulo.setId("labelChess");

        Button buttonMenu = new MyButton("Menu", 580, 670, 200, 50);
        buttonMenu.getStyleClass().add("roundedButton");
        buttonMenu.setOnAction(e -> {

            ((Stage) (((Node) e.getSource()).getScene().getWindow())).setScene(new Scene(new Inicio()));
        });

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER_LEFT);

//Label titulo1 = new MyLabel("Hello",200,200,500,100);
        Label nombre = new MyLabel("NOMBRE", 150, 150, 500, 100);

        Label nickname = new MyLabel("NICKNAME", 150, 200, 500, 100);

        Label nacionalidad = new MyLabel("NACIONALIDAD", 150, 250, 500, 100);

        Label edad = new MyLabel("EDAD", 150, 300, 500, 100);

        Label genero = new MyLabel("GENERO", 150, 350, 500, 100);

        Label imagen = new MyLabel("Direccion Imagen 32x32", 150, 450, 500, 100);

        TextField tnombre = new MyTextField("", 300, 180, 400, 10);

        TextField tnickname = new MyTextField("", 300, 230, 400, 10);

        TextField tnacionalidad = new MyTextField("", 300, 280, 400, 10);

        TextField tedad = new MyTextField("", 300, 330, 400, 10);

        TextField tgenero = new MyTextField("", 300, 380, 400, 10);

        TextField timagen = new MyTextField("", 300, 530, 400, 10);

        Button buttonAceptar = new MyButton("REGISTRAR", 300, 670, 250, 50);
        buttonAceptar.getStyleClass().add("roundedButton");
        buttonAceptar.setOnAction(e -> {

            FileWriter archivo = null;
            PrintWriter escritor = null;

            try {
                archivo = new FileWriter("usuarios.txt",true);
                escritor = new PrintWriter(archivo);

                escritor.println(tnombre.getText()+","+tnickname.getText()+","+tnacionalidad.getText()+","+ tedad.getText()+","+tgenero.getText()+","+
                  timagen.getText()+","+0+","+0+","+0+","+0);
            tedad.setText("");
            tnacionalidad.setText("");
            tnombre.setText("");
            tgenero.setText("");
            timagen.setText("");
            tnickname.setText("");
                
                
                
                
            } catch (Exception i) {
                System.out.println("Error");
            } finally {
                try {
                    archivo.close();
                } catch (IOException ex) {
                    Logger.getLogger(RegistrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            
        });

        this.getChildren().addAll(titulo, buttonMenu, nombre, nickname, nacionalidad, edad, genero, imagen,
                tnombre, tedad, tgenero, timagen, tnacionalidad, tnickname, buttonAceptar);

    }

}
