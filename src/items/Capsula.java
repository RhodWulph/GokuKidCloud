package items;

import game.Escenario;
import game.Objetos;

public class Capsula extends Objetos {

	public static boolean GENKIDAMA = false;
	public static boolean KRILLIN = false;
	public static boolean GOHAN = false;

	public Capsula(Escenario escenario) {
		super(escenario);
		setSpritesNombres(new String[] { "items/capsula.png" });
		setPremio();
	}

	public void accion() {
		super.accion();
	}

	private void setPremio() {
		
		GENKIDAMA = false;
		KRILLIN = false;
		GOHAN = false;
		
		int posibilidad = (int) (Math.random() * 3 + 1);
		switch (posibilidad) {
		case 1:
			GENKIDAMA = true;
			break;

		case 2:
			KRILLIN = true;
			break;

		case 3:
			GOHAN = true;
			break;

		}
	}

}
