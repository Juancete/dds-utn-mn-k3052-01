package ar.com.aterrizar.arena;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;

import ar.com.aterrizar.daos.AterrizarCom;
import ar.com.aterrizar.entidades.Asiento;
import ar.com.aterrizar.modelo.Usuario;
import ar.com.aterrizar.modelo.state.EstadoReservado;

public class sobreReservaDialog extends Window<Asiento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario miUsuario;

	public sobreReservaDialog(WindowOwner owner, Asiento model, Usuario unUsuario) {
		super(owner, model);
		this.miUsuario = unUsuario;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createContents(Panel mainPanel) {
		this.setTitle("Aterrizar.com");
		mainPanel.setLayout(new VerticalLayout());
		new Label(mainPanel).setText("El asiento ya se encuentra reservado");
		new Label(mainPanel).setText("¿Que desea hacer?");
		Panel form = new Panel(mainPanel);
		form.setLayout(new HorizontalLayout());
		this.addActions(form);
	}
	
	 protected void addActions(Panel actionsPanel) {
			Button sobreReservar = new Button(actionsPanel).setCaption("Sobre-reservar");
			sobreReservar.onClick(new MessageSend(this, "sobreReservar"));
			Button cerrar = new Button(actionsPanel).setCaption("Seguir buscando");
			cerrar.onClick(new MessageSend(this, "close"));
     }

	public void sobreReservar(){
		//AterrizarCom.getInstance().getHome(EstadoReservado.class).create((EstadoReservado) ((Asiento)this.getModelObject()).getEstado());
		((EstadoReservado)this.getModelObject().getEstado()).sobreReservar(this.getModelObject(), this.miUsuario);
		close();
	}
}
