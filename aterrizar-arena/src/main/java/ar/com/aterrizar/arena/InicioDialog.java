package ar.com.aterrizar.arena;





import java.util.Arrays;

import org.uqbar.arena.bindings.DateAdapter;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import tadp.blocbaster.daos.Videoclub;
import tadp.blocbaster.entidades.Ciudad;
import tadp.blocbaster.entidades.Socio;

import ar.com.aterrizar.modelo.Usuario;

public class InicioDialog extends Dialog<Usuario>{
	Usuario usuario;

	public InicioDialog(WindowOwner owner, Usuario model) {
		super(owner, model);
		this.usuario = model;
		usuario.setNombre("Nacho");
	}
	

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("aterrizar.com");
		mainPanel.setLayout(new VerticalLayout());
		
		new Label(mainPanel).setText("Hola "+this.usuario.getNombre());
		new Label(mainPanel).setText("¿Que desea hacer?");
		
		new Button(mainPanel).setCaption("Boton");
		new Button(mainPanel).setCaption("Boton2");
		/*Panel form = new Panel(mainPanel);
		form.setLayout(new ColumnLayout(2));

		new Label(form).setText("Nombre");
	

		new Label(form).setText("Direccion");
	

		new Label(form).setText("Fecha de Ingreso");
	
		
		// combo al enum de estado
		new Label(form).setText("Estado");
	
		
		new Label(form).setText("Ciudad");
	
		
	}

}
