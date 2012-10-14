package ar.com.aterrizar.arena;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.MainWindow;

import ar.com.aterrizar.commons.model.ShowModel;
import ar.com.aterrizar.daos.AterrizarCom;
import ar.com.aterrizar.modelo.NivelPago;
import ar.com.aterrizar.modelo.Usuario;
import ar.com.aterrizar.modelo.state.EstadoComprado;
import ar.com.aterrizar.modelo.state.EstadoReservado;

@SuppressWarnings("serial")
public class InicioWindow extends MainWindow<Usuario> {
	public Usuario usuario;

	public static void main(String[] args) {
		new InicioWindow().startApplication();

	}

	public InicioWindow() {
		super(new Usuario());
		this.usuario = new Usuario();
		usuario.setNombre("Nacho");
		usuario.setNivel(new NivelPago(usuario));
		usuario.setDNI("28999189");
	}

	public Usuario getUsuario() {
		return usuario;
	}

	@Override
	public void createContents(Panel mainPanel) {
		this.setTitle("aterrizar.com");
		mainPanel.setLayout(new VerticalLayout());

		new Label(mainPanel).setText("Hola " + this.usuario.getNombre());
		new Label(mainPanel).setText("¿Que desea hacer?");

		Panel actionsPanel = new Panel(mainPanel);
		actionsPanel.setLayout(new HorizontalLayout());

		Button verCompras = new Button(actionsPanel).setCaption("Ver compras");
		verCompras.onClick(new MessageSend(this, "verCompras"));

		Button verReservas = new Button(actionsPanel)
				.setCaption("Ver reservas");
		verReservas.onClick(new MessageSend(this, "verReservas"));

		Button buscarAsientos = new Button(actionsPanel)
				.setCaption("Buscar Asientos");
		buscarAsientos.onClick(new MessageSend(this, "buscarAsientos"));

	}

	public void verCompras() {
		new VistaWindow("Compras de ", this, new ShowModel<EstadoComprado>(
				AterrizarCom.getInstance().getHome(EstadoComprado.class)))
				.open();
	}

	public void verReservas() {
		new VistaWindow("Reservas de ", this, new ShowModel<EstadoReservado>(
				AterrizarCom.getInstance().getHome(EstadoReservado.class)))
				.open();
	}

	public void buscarAsientos() {
		// new VistaWindow(this, this.getModelObject()).open();
		new BuscarAsientoWindow(this).open();
	}
}
