package personajes;

import game.Animado;
import game.Escenario;
import game.Personajes;
import items.Capsula;
import items.Comidas;

import java.awt.event.KeyEvent;

import poderes.PoderGokuDebil;
import poderes.PoderGokuFuerte;

public class Goku extends Personajes {

	public static final int DAMAGE = 20;

	public static final int MAX_VIDA = 250;
	public static final int JUGADOR_VELOCIDAD = 4;
	public static final int MAX_GENKIDAMA = 4;
	public static final int MAX_KRILLIN = 2;
	public static final int MAX_GOHAN = 2;

	private boolean arriba, abajo, derecha, izquierda;

	private int puntuacion = 0;
	private int genkidama = 1;
	private int krillin = 0;
	private int gohan = 0;

	public Goku(Escenario escenario) {
		super(escenario);
		setSpritesNombres(new String[] { "personajes/kidGoku0.png",
				"personajes/kidGoku1.png" });
		setVelocidadFrame(35);
		setVitalidad(MAX_VIDA);

	}

	public void accion() {
		super.accion();
		setCoordenadaX(getCoordenadaX() + getVelocidadX());
		setCoordenadaY(getCoordenadaY() + getVelocidadY());

		if (getCoordenadaX() < 0)
			setCoordenadaX(0);
		if (getCoordenadaX() > Escenario.ANCHO - getAncho())
			setCoordenadaX(Escenario.ANCHO - getAncho());
		if (getCoordenadaY() < 0)
			setCoordenadaY(0);
		if (getCoordenadaY() > Escenario.ALTO_JUGABLE - getAlto())
			setCoordenadaY(Escenario.ALTO_JUGABLE - getAlto());
	}

	// ********************************

	public void keyPressed(KeyEvent evex) {
		switch (evex.getKeyCode()) {

		case KeyEvent.VK_UP:
			arriba = true;
			break;

		case KeyEvent.VK_DOWN:
			abajo = true;
			break;

		case KeyEvent.VK_RIGHT:
			derecha = true;
			break;

		case KeyEvent.VK_LEFT:
			izquierda = true;
			break;

		}
		actualizarVelocidad();
	}

	public void keyReleased(KeyEvent evex) {
		switch (evex.getKeyCode()) {

		case KeyEvent.VK_UP:
			arriba = false;
			break;

		case KeyEvent.VK_DOWN:
			abajo = false;
			break;

		case KeyEvent.VK_RIGHT:
			derecha = false;
			break;

		case KeyEvent.VK_LEFT:
			izquierda = false;
			break;

		case KeyEvent.VK_SPACE:
			poderDebil();
			break;

		case KeyEvent.VK_C:
			poderFuerte();
			break;

		case KeyEvent.VK_V:
			invocarGohan();
			break;

		case KeyEvent.VK_B:
			invocarKrillin();
			break;

		}
		actualizarVelocidad();
	}

	protected void actualizarVelocidad() {
		setVelocidadX(0);
		setVelocidadY(0);

		if (arriba)
			setVelocidadY(-JUGADOR_VELOCIDAD);
		if (abajo)
			setVelocidadY(JUGADOR_VELOCIDAD);

		if (derecha)
			setVelocidadX(JUGADOR_VELOCIDAD);

		if (izquierda)
			setVelocidadX(-JUGADOR_VELOCIDAD);

	}

	// ********************************

	@Override
	public void colisiones(Animado animado) {

		colisionesAliado(animado);

		if (animado instanceof Capsula) {
			animado.remover();
			escenario.getSonidoCache().playSonido("audio/toque.wav");
			if (Capsula.GENKIDAMA)
				addGenkidama(1);

			else if (Capsula.KRILLIN)
				addKrillin(1);

			else if (Capsula.GOHAN)
				addGohan(1);

		}
		if (animado instanceof Comidas) {
			animado.remover();
			escenario.getSonidoCache().playSonido("audio/toque.wav");
			addVitalidad(Comidas.VITALIDAD);
		}

		if (getVitalidad() <= 0) {
			escenario.setGokuVivo(false);
			escenario.setJuegoTerminado(true);
		}
	}

	// ********************************

	@Override
	public void poderDebil() {
		PoderGokuDebil poderDebil = new PoderGokuDebil(escenario);
		poderDebil.setCoordenadaX(coordenadaX + getAncho() - 5);
		poderDebil.setCoordenadaY(coordenadaY);
		escenario.addAnimado(poderDebil);
		escenario.getSonidoCache().playSonido("audio/gokuDebil.wav"
				+ "");
	}

	@Override
	public void poderFuerte() {
		if (getGenkidama() > 0) {
			genkidama--;
			PoderGokuFuerte poderFuerte = new PoderGokuFuerte(escenario);
			poderFuerte.setCoordenadaX(coordenadaX + getAncho() - 5);
			poderFuerte.setCoordenadaY(coordenadaY);
			escenario.addAnimado(poderFuerte);
			escenario.getSonidoCache().playSonido("audio/genkidama.wav");
		}

	}

	public void invocarKrillin() {
		if (getKrillin() > 0) {
			krillin--;
			Krillin krillin = new Krillin(escenario);
			krillin.setCoordenadaX(coordenadaX + getAncho() + 5);
			krillin.setCoordenadaY(coordenadaY + 5);
			escenario.addAnimado(krillin);
		}
	}

	public void invocarGohan() {
		if (getGohan() > 0) {
			gohan--;
			Gohan gohan = new Gohan(escenario);
			gohan.setCoordenadaX(coordenadaX + getAncho() + 5);
			gohan.setCoordenadaY(coordenadaY + 5);
			escenario.addAnimado(gohan);
		}
	}

	// ********************************

	// Set, get y add de vida y puntos
	public void setPuntuacion(int puntos) {
		puntuacion = puntos;
	}

	public int getPuntiacion() {
		return puntuacion;
	}

	public void addPuntuacion(int puntos) {
		puntuacion += puntos;
	}

	@Override
	public void addVitalidad(int vida) {
		int vidaRestante = MAX_VIDA - vitalidad;
		if (vida >= vidaRestante)
			vitalidad = MAX_VIDA;
		else
			vitalidad += vida;
	}

	// ********************************

	public void setGenkidama(int gd) {
		genkidama = gd;
	}

	public int getGenkidama() {
		return genkidama;
	}

	public void addGenkidama(int gd) {
		int restante = MAX_GENKIDAMA - genkidama;
		if (restante > 0) {
			genkidama += gd;
		}
	}

	public void setKrillin(int kr) {
		krillin = kr;
	}

	public int getKrillin() {
		return krillin;
	}

	public void addKrillin(int kr) {
		int restante = MAX_KRILLIN - krillin;
		if (restante > 0) {
			krillin += kr;
		}
	}

	public void setGohan(int gh) {
		gohan = gh;
	}

	public int getGohan() {
		return gohan;
	}

	public void addGohan(int gh) {
		int restante = MAX_GOHAN - gohan;
		if (restante > 0) {
			gohan += gh;
		}
	}
}
