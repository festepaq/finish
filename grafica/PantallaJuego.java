package grafica;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logica.ColorPieza;
import logica.Juego;
import logica.PosicionAjedrez;
/**
 * PantallaJuego es la pantalla de juego principal donde se grafican los
 * distintos botones de la pantalla, el {@link Tablero}, la {@link TablaJugadas} y el
 * {@link EstadoDeJuego} y crea al {@link Juego}
 * 
 *
 */
public class PantallaJuego extends Pane implements Dimensiones{

	private ControladorJuego controlador;
	private Tablero elTablero;
	private Juego elJuego;
	private EstadoDeJuego estado;
	private Button enroqueLargo;
	private Button enroqueCorto;
	private int cantJugadores;
	private ColorPieza colorElegido;
	
	/**
	 *  Constructor para crear una pantalla de Juego con un juego nuevo
	 *  se llama a si mismo con un juego null para indicar que hay que crear un juego nuevo
	 * @param cantJugadores cantidad de jugadores humanos
	 * @param colorElegido color de piezas que elijio el usuario en caso
	 * 			de ser dos jugadores es blanco.
	 */
	public PantallaJuego(int cantJugadores, ColorPieza colorElegido){
		this(null,cantJugadores,colorElegido);
	}
	
	/**
	 * Constructor que inicializa el Juego si es null como un juego nuevo sino como el pasado 
	 * por parametro.
	 * @param juego
	 * @param cantJugadores
	 * @param colorElegido
	 */
	public PantallaJuego(Juego juego,int cantJugadores, ColorPieza colorElegido){
		this.cantJugadores = cantJugadores;
		this.colorElegido = colorElegido;
		
		this.setPrefSize(900, 800);
		this.getStylesheets().add(getClass().getResource("../assets/application.css").toExternalForm());
		
		enroqueCorto = new MyButton("",770,520,70,70);
		enroqueLargo = new MyButton("",680,520,70,70);
		
		if(juego == null){
			elJuego= new Juego();
		}else{
			elJuego = juego;
		}
		
		estado= new EstadoDeJuego(elJuego,enroqueLargo,enroqueCorto);
		elTablero = new Tablero(elJuego);
		
		controlador=new ControladorJuego(cantJugadores,colorElegido,elTablero,elJuego,estado);
		
		inicializarMenu();
	}
	
	/**
	 * crea los botones y valida los clicks hechos en el tablero
	 */
	private void inicializarMenu(){
		Label titulo = new MyLabel("",200,20,500,100);
			titulo.setAlignment(Pos.TOP_CENTER);
			titulo.setId("labelChess");
		
		
		
		Button buttonDeshacer = new MyButton("Deshacer",360,670,200,50);
			buttonDeshacer.setOnAction(e->controlador.deshacerJugada());
			buttonDeshacer.getStyleClass().add("roundedButton");
		
		Button buttonMenu = new MyButton("Menu",580,670,200,50);
			buttonMenu.getStyleClass().add("roundedButton");
			buttonMenu.setOnAction(e ->{

				((Stage)(((Node) e.getSource()).getScene().getWindow())).setScene(new Scene( new Inicio()));
			});
			

			enroqueLargo.setOnAction(e->controlador.enroqueLargo());
			enroqueLargo.getStyleClass().add("enroqueButton");
			enroqueLargo.getStyleClass().add("enroqueLargoButton");
			
			enroqueCorto.setOnAction(e->controlador.enroqueCorto());
			enroqueCorto.getStyleClass().add("enroqueButton");
			enroqueCorto.getStyleClass().add("enroqueCortoButton");
			
		this.setOnMouseClicked( e->{
			double x=e.getSceneX();
			double y=e.getSceneY();
			if(x>DES_TABLERO_X && x<DES_TABLERO_X+TABLERO_ANCHO && y>DES_TABLERO_Y && y<DES_TABLERO_Y+TABLERO_ALTO){
				controlador.jugar( convertirClick( x , y ) );
			}
		} );

		this.getChildren().addAll(titulo,buttonDeshacer,buttonMenu,enroqueCorto,enroqueLargo,elTablero,estado);
	}
	
	/**
	 * Convierte de una posicion de pixeles a {@link PosicionAjedrez}
	 * @param x
	 * @param y
	 * @return PosicionAjedrez
	 */
	private PosicionAjedrez convertirClick(double x, double y){
		int fila= (int) ((y-DES_TABLERO_Y)/CASILLERO_ALTO);
		int columna= (int) ((x-DES_TABLERO_X)/CASILLERO_ANCHO);
		return aPosAjedrez(fila, columna);
	}
	/**
	 * Convierte de una poscion de Tablero a una {@link PosicionAjedrez}
	 * @param fila
	 * @param columna
	 * @return
	 */
	private PosicionAjedrez aPosAjedrez(int fila, int columna){
		byte laFila = (byte) (TABLERO_FILAS - fila);
		char laColumna = (char) (columna + 'a');
		return new PosicionAjedrez(laFila, laColumna);
	}
	
}
