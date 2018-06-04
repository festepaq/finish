/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafica;

import javafx.scene.control.TextField;

/**
 *
 * @author Farid Estepa
 */
public class MyTextField extends TextField{
 
    public MyTextField(String text, int x, int y, int width, int height){
		super();
		this.setText(text);
               
		this.setPrefSize(width,height);
		this.setTranslateX(x);
		this.setTranslateY(y);
	}
}
