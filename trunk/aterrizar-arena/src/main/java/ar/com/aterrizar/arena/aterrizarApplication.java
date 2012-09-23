package ar.com.aterrizar.arena;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

import ar.com.aterrizar.modelo.Usuario;

public class aterrizarApplication extends Application {

	public static void main(String[] args) {
		new aterrizarApplication().start();
		
	}

	@Override
	protected Window<?> createMainWindow() {
		return new InicioDialog(this, new Usuario());
	}
}
