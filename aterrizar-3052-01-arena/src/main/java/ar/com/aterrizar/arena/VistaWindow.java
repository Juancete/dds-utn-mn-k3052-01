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
import ar.com.aterrizar.modelo.state.Estado;

import com.uqbar.commons.collections.Transformer;

@SuppressWarnings("serial")
public class VistaWindow extends Window<ShowModel<? extends Entity>> {

	private String action;

	public VistaWindow(String unaAccion, InicioWindow owner,
			ShowModel<? extends Entity> modelo) {
		super(owner, modelo);
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
		Table<Estado> table = new Table<Estado>(mainPanel, Estado.class);

		table.bindItemsToProperty(ShowModel.RESULTS);

		Column<Estado> salidaColumn = new Column<Estado>(table);
		salidaColumn.setTitle("Salida");
		salidaColumn.setFixedSize(100);
		salidaColumn
				.bindContentsToTransformer(new Transformer<Estado, String>() {
					@Override
					public String transform(Estado estado) {
						return new SimpleDateFormat("dd/MM/yyyy").format(estado
								.getMiAsiento().getVuelo().getFechaOrigen()
								.obtenerFecha());
					}
				});

		Column<Estado> aerolineaColumn = new Column<Estado>(table);
		aerolineaColumn.setTitle("Aerolinea");
		aerolineaColumn.setFixedSize(100);
		aerolineaColumn
				.bindContentsToTransformer(new Transformer<Estado, String>() {
					@Override
					public String transform(Estado estado) {
						return estado.getMiAsiento().getAerolinea().getNombre();
					}
				});
		Column<Estado> vueloColumn = new Column<Estado>(table);
		vueloColumn.setTitle("Vuelo");
		vueloColumn.setFixedSize(100);
		vueloColumn
				.bindContentsToTransformer(new Transformer<Estado, String>() {
					@Override
					public String transform(Estado estado) {
						if (estado.getMiAsiento().getVuelo() == null) {
							return "Sin especificar";
						}

						return estado.getMiAsiento().getVuelo().getCodigo();
					}
				});
		Column<Estado> asientoColumn = new Column<Estado>(table);
		asientoColumn.setTitle("Asiento");
		asientoColumn.setFixedSize(100);
		asientoColumn
				.bindContentsToTransformer(new Transformer<Estado, String>() {
					@Override
					public String transform(Estado estado) {
						return estado.getMiAsiento().getCodigo();
					}
				});
		Column<Estado> precioColumn = new Column<Estado>(table);
		precioColumn.setTitle("Precio");
		precioColumn.setFixedSize(100);
		precioColumn
				.bindContentsToTransformer(new Transformer<Estado, String>() {
					@Override
					public String transform(Estado estado) {
						return estado.getMiAsiento().getPrecio().toString();
					}
				});
		table.setHeigth(300);
		table.setWidth(600);
	}

	private void addActions(Panel mainPanel) {

		Button cerrar = new Button(mainPanel).setCaption("Cerrar");
		cerrar.onClick(new MessageSend(this, "close"));
	}
}
