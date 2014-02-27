package poderes;

import game.Escenario;
import game.Poderes;

public class PoderKrillin extends Poderes {

	public static int DAMAGE = 20; 

	public PoderKrillin(Escenario escenario) {
		super(escenario);
		setSpritesNombres(new String[] {"poderes/poderKrillin0.png", "poderes/poderKrillin1.png", "poderes/poderKrillin2.png"});
		setVelocidadFrame(20);
		setVelocidadPoder(7);
	}

	public void accion(){
		super.accion();
		poderAliado();
		
	}
	
}
