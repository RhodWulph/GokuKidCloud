package poderes;

import game.Escenario;
import game.Poderes;

public class PoderGohan extends Poderes{
	
	public static int DAMAGE = 10; 

	public PoderGohan(Escenario escenario) {
		super(escenario);
		setSpritesNombres(new String[] {"poderes/poderGohan.png"});
		setVelocidadPoder(7);
	}

	@Override
	public void accion(){
		super.accion();
		poderAliado();
	}
	
}
