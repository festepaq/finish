package grafica;

import logica.ColorPieza;
import logica.Juego;
import logica.PosicionAjedrez;

/**
 * Clase que adimitstra las entradas del usurario y verifica con el {@link Juego} el flujo
 * del juego 
 * 
 *
 */
public class ControladorJuego {
	private Jugador jugadorBlanco, jugadorNegro, jugadorTurno;
	private Juego elJuego;
	private final boolean esUnJugador;
	private Tablero elTablero;
	private EstadoDeJuego estado;
	
	
	/**
	 * Constructor del Juego que si es un jugador 
	 * @param cantJugadores
	 * @param colorElegido
	 * @param tablero referencia al {@link Tablero}
	 * @param elJuego referencia al Juego
	 * @param estado referencia al {@link EstadoDeJuego}
	 */
	public ControladorJuego (int cantJugadores,ColorPieza colorElegido, Tablero tablero,Juego elJuego,EstadoDeJuego estado){
		this.elJuego=elJuego;
		esUnJugador = (cantJugadores == 1);
		elTablero = tablero;
		this.estado=estado;		
		if(esUnJugador){
//			if(colorElegido == ColorPieza.BLANCO){
//				jugadorBlanco= new JugadorHumano(elJuego,ColorPieza.BLANCO);
//				jugadorNegro= new JugadorInteligencia(elJuego,ColorPieza.NEGRO);
//			}else{
//				jugadorBlanco= new JugadorInteligencia(elJuego,ColorPieza.BLANCO);
//				jugadorNegro= new JugadorHumano(elJuego,ColorPieza.NEGRO);
//			}
		}else{
			jugadorBlanco= new JugadorHumano(elJuego,ColorPieza.BLANCO);
			jugadorNegro= new JugadorHumano(elJuego,ColorPieza.NEGRO);
		}
		
	}
	
	/**
	 * actualiza el juagador con el turno actual
	 */
	private void actualizoJugadorTurno(){
		if(elJuego.dameTurno() == ColorPieza.BLANCO){
			jugadorTurno = jugadorBlanco;
		}else{
			jugadorTurno = jugadorNegro;
		}
	}
	
	/**
	 * Método que deshcae la jugada y en caso de jugar contra una inteligencia artificial lo
	 * hace dos veces.
	 */
	public void deshacerJugada(){
		if(elJuego.huboUnaJugada()){
			actualizoJugadorTurno();
			if(esUnJugador && jugadorTurno instanceof JugadorHumano){
				elJuego.revertir();
				
			}
			elJuego.revertir();
			elTablero.imprimirTablero();
			estado.actualizarEstado();
		}
	}
	
	/**
	 * Metodo que si es posible hace el enroque largo
	 */
	public void enroqueLargo(){
		if(elJuego.sePuedeEnrocarLargo()){
			elJuego.enrocarLargo();
			
			elTablero.imprimirTablero();
			estado.actualizarEstado();
		}
	}
	
	/**
	 * Metodo que si es posible hace el enroque corto
	 */
	public void enroqueCorto(){
		if(elJuego.sePuedeEnrocarCorto()){
			elJuego.enrocarCorto();
		
			elTablero.imprimirTablero();
			estado.actualizarEstado();
		}
	}
	
	/**
	 * Hace que el jugador con el turno actual juegue y luego imprime la jugada
	 * @param pos
	 */
	public void jugar(PosicionAjedrez pos){
		actualizoJugadorTurno();

		
		jugadorTurno.jugar(pos);//hago la jugada
		elTablero.imprimirTablero();
		//si no juega la ia, o es multiplayer
		if( jugadorTurno instanceof JugadorHumano ){
			JugadorHumano jg = ((JugadorHumano) jugadorTurno);
			if( jg.deboPintarCasilleros() ){
				elTablero.pintarCasilleros(jg.dameCasillerrosPintar());
			}
		}
		estado.actualizarEstado();
		if(jugadorTurno.huboUnaJugada()){
	
		}
	}
	
	
	
}
