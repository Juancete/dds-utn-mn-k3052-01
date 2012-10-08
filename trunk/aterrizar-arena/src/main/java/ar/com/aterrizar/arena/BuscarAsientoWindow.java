package ar.com.aterrizar.arena;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Control;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.commons.model.SearchByExample;

import ar.com.aterrizar.arena.SearchAsientoWindow;

import ar.com.aterrizar.daos.AterrizarCom;
import ar.com.aterrizar.entidades.Asiento;

import com.uqbar.commons.collections.Transformer;

/**
 * Representa la ventana de busqueda de socios del videoclub.
 * 
 * @author anonimo XD
 */
public class BuscarAsientoWindow extends SearchAsientoWindow<Asiento, SearchAsientoByExample<Asiento>> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BuscarAsientoWindow(InicioWindow owner) {
		super(owner, new SearchAsientoByExample<Asiento>(AterrizarCom.getInstance().getHome(Asiento.class), owner.getUsuario()));
	}

	@Override
	protected void createMainTemplate(Panel formBuilder) {
		this.setTitle("Buscador de Asientos");
		this.setTaskDescription("Ingrese los parámetros de búsqueda");
		super.createMainTemplate(formBuilder);
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel);
		searchFormPanel.bindContents(SearchByExample.EXAMPLE);
		searchFormPanel.setLayout(new ColumnLayout(2));

		// Field nombre
		Label nombreLabel = new Label(searchFormPanel);
		nombreLabel.setText("Origen");

		Control nombre = new TextBox(searchFormPanel);
		nombre.bindValueToProperty(Asiento.ORIGEN);

		// Field direccion
		Label direccionLabel = new Label(searchFormPanel);
		direccionLabel.setText("Destino");

		Control direccion = new TextBox(searchFormPanel);
		direccion.bindValueToProperty(Asiento.DESTINO);

		Label fechaLabel = new Label(searchFormPanel);
		fechaLabel.setText("Fecha");
		
		Control fecha = new TextBox(searchFormPanel);
		fecha.bindValueToProperty(Asiento.FECHA);
	}

	@Override
	protected void describeResultsGrid(Table<Asiento> table) {
		Column<Asiento> aerolineaColumn = new Column<Asiento>(table);
		aerolineaColumn.setTitle("Aerolinea");
		aerolineaColumn.setFixedSize(200);
		aerolineaColumn.bindContentsToTransformer(new Transformer<Asiento, String>() {
			@Override
			public String transform(Asiento asiento) {
				return asiento.getAerolinea().getNombre();
			}			
		});

		Column<Asiento> vueloColumn = new Column<Asiento>(table);
		vueloColumn.setTitle("Vuelo");
		vueloColumn.setFixedSize(200);
		vueloColumn.bindContentsToTransformer(new Transformer<Asiento, String>() {
			@Override
			public String transform(Asiento asiento) {
				return asiento.getVuelo().getCodigo();
			}
		});

		Column<Asiento> asientoColumn = new Column<Asiento>(table);
		asientoColumn.setTitle("asiento");
		asientoColumn.setFixedSize(200);
		asientoColumn.bindContentsToTransformer(new Transformer<Asiento, String>() {
			@Override
			public String transform(Asiento asiento) {
				return asiento.getCodigo();
			}
		});
		Column<Asiento> precioColumn = new Column<Asiento>(table);
		precioColumn.setTitle("precio");
		precioColumn.setFixedSize(200);
		precioColumn.bindContentsToTransformer(new Transformer<Asiento, String>() {
			@Override
			public String transform(Asiento asiento) {
				return asiento.getPrecio().toString() ;
			}
		});	
			
		
		table.setHeigth(300);
		table.setWidth(600);
	}

//	@Override
//	protected void addActions(Panel actionsPanel) {
//		Button nuevoSocio = new Button(actionsPanel);
//		nuevoSocio.setCaption("Nuevo Socio");
//		nuevoSocio.onClick(new MessageSend(this, "crearSocio"));
//		
//		super.addActions(actionsPanel);
//	}
//
//	// ********************************************************
//	// ** Acciones
//	// ********************************************************
//
//	public void crearSocio() {
//		Dialog<?> crearSocio = new CrearSocioDialog(this);
//		crearSocio.onAccept(new MessageSend(this.getModelObject(), Search.SEARCH));
//		crearSocio.open();
//	}
//
//	@Override
//	protected Dialog<?> createEditor(Asiento selected) {
//		return new ModificarSocioDialog(this, selected);
//	}

	// @Override
	// protected void addActions(Panel actions) {
	// Button buscar= actions.addButton();
	// buscar.setModelAction("search");
	// buscar.setAsDefault();
	//
	// Button agregar = actions.addButton();
	// agregar.setPanelAction("agregarSocio");
	// }
	//
	// protected void agregarSocio() {
	// Dialog agregarSocio = new CrearSocioPanel(...);
	// agregarSocio.onClose(this, "search");
	// agregarSocio onCloseSend: #search to: self
	// }

}
