package personajes;

import game.Animado;
import game.Escenario;
import game.Personajes;
import poderes.PoderGohan;

public class Gohan extends Personajes {

	public static final int DAMAGE = 35;

	public Gohan(Escenario escenario) {
		super(escenario);
		setSpritesNombres(new String[] { "personajes/aliadoGohan0.png",
				"personajes/aliadoGohan1.png", "personajes/aliadoGohan2.png" });
		setVelocidadFrame(35);
		setVitalidad(50);
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
	public void colisiones(Animado animado) {
		colisionesAliado(animado);
		if (getVitalidad() <= 0)
			remover();
	}

	@Override
	public void poderDebil() {
		PoderGohan poder = new PoderGohan(escenario);
		poder.setCoordenadaX(coordenadaX + getAncho());
		poder.setCoordenadaY(coordenadaY);
		escenario.addAnimado(poder);
	}
	
	@Override
	public void poderFuerte() {
		
	}

	@Override
	public void addVitalidad(int v) {
		vitalidad += v;
	}

}
