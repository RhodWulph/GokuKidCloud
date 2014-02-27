package personajes;

import game.Animado;
import game.Escenario;
import game.Personajes;
import poderes.PoderKrillin;

public class Krillin extends Personajes {
	
	public static final int DAMAGE = 35;
	
	public Krillin(Escenario escenario) {
		super(escenario);
		setSpritesNombres(new String[] { "personajes/aliadoKrillin0.png",
				"personajes/aliadoKrillin1.png",
				"personajes/aliadoKrillin2.png" });
		setVelocidadFrame(35);
		setVitalidad(70);
	}

	@Override
	public void accion() {
		super.accion();
		sinMovimiento();
		if (Math.random() < FRECUENCIA_ATAQUE) {
			poderDebil();
		}

	}

	@Override
	public void poderDebil() {
		PoderKrillin poder = new PoderKrillin(escenario);
		poder.setCoordenadaX(coordenadaX + getAncho());
		poder.setCoordenadaY(coordenadaY);
		escenario.addAnimado(poder);
	}
	
	@Override
	public void poderFuerte() {		
	}

	// ********************************

	@Override
	public void colisiones(Animado animado) {
		colisionesAliado(animado);
		
		if (getVitalidad() <= 0)
			remover();
	}

	@Override
	public void addVitalidad(int v) {
		vitalidad += v;
	}


}
