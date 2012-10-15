package ar.com.aterrizar.arena;

import java.text.SimpleDateFormat;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Window;
import org.uqbar.commons.model.Entity;

import ar.com.aterrizar.commons.model.ShowModel;
import ar.com.aterrizar.entidades.Asiento;
import com.uqbar.commons.collections.Transformer;

@SuppressWarnings("serial")
public class VistaWindow extends Window<ShowModel<? extends Entity>> {

	private String action;

	public VistaWindow(String unaAccion, InicioWindow owner, ShowModel<Asiento> showModel) {
		super(owner, showModel);
		action = unaAccion;
	}

	public String getAction() {
		return action;
	}

	@Override
	public void createContents(Panel mainPanel) {
		this.setTitle("Aterrizar.com");
		mainPanel.setLayout(new VerticalLayout());
		this.setSecondTitle(mainPanel);
		this.createGrid(mainPanel);
		this.addActions(mainPanel);
	}

	public void setSecondTitle(Panel mainPanel) {
		InicioWindow owner = (InicioWindow) this.getOwner();
		new Label(mainPanel).setText(this.getAction()
				+ owner.getUsuario().getNombre());
	}

	public void createGrid(Panel mainPanel) {
		Table<Asiento> table = new Table<Asiento>(mainPanel, Asiento.class);

		table.bindItemsToProperty(ShowModel.RESULTS);

		Column<Asiento> salidaColumn = new Column<Asiento>(table);
		salidaColumn.setTitle("Salida");
		salidaColumn.setFixedSize(100);
		salidaColumn
				.bindContentsToTransformer(new Transformer<Asiento, String>() {
					@Override
					public String transform(Asiento unAsiento) {
						return new SimpleDateFormat("dd/MM/yyyy").format(
								unAsiento.getVuelo().getFechaOrigen()
								.obtenerFecha());
					}
				});

		Column<Asiento> aerolineaColumn = new Column<Asiento>(table);
		aerolineaColumn.setTitle("Aerolinea");
		aerolineaColumn.setFixedSize(100);
		aerolineaColumn
				.bindContentsToProperty("nombreDeAerolinea");
		
		Column<Asiento> vueloColumn = new Column<Asiento>(table);
		vueloColumn.setTitle("Vuelo");
		vueloColumn.setFixedSize(100);
		vueloColumn
				.bindContentsToProperty("codigoDeVuelo");

		Column<Asiento> asientoColumn = new Column<Asiento>(table);
		asientoColumn.setTitle("Asiento");
		asientoColumn.setFixedSize(100);
		asientoColumn
				.bindContentsToProperty("codigo");

		Column<Asiento> precioColumn = new Column<Asiento>(table);
		precioColumn.setTitle("Precio");
		precioColumn.setFixedSize(100);
		precioColumn
				.bindContentsToProperty("precio");

		table.setHeigth(300);
		table.setWidth(600);
	}

	private void addActions(Panel mainPanel) {

		Button cerrar = new Button(mainPanel).setCaption("Cerrar");
		cerrar.onClick(new MessageSend(this, "close"));
	}
}
