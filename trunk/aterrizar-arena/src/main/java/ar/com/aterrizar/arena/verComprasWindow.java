package ar.com.aterrizar.arena;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.MainWindow;

import ar.com.aterrizar.modelo.Usuario;

public class verComprasWindow extends Dialog<Usuario>{
	
	public verComprasWindow(InicioWindow owner, Usuario usuario) {
		super(owner, usuario);
	}



@Override
protected void createFormPanel(Panel mainPanel) {
	// TODO Auto-generated method stub
	this.setTitle("soy la ventana de compras");
	mainPanel.setLayout(new VerticalLayout());
}

}
