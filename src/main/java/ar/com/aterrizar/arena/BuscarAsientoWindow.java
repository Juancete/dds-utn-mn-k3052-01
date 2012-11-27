package ar.com.aterrizar.arena;

import java.text.SimpleDateFormat;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Control;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.commons.model.Search;
import org.uqbar.commons.model.SearchByExample;

import com.uqbar.commons.collections.Transformer;

import ar.com.aterrizar.arena.appmodel.SearchAsientoByExample;
import ar.com.aterrizar.arena.appmodel.ShowBusquedaModel;
import ar.com.aterrizar.daos.AterrizarCom;
import ar.com.aterrizar.daos.BusquedaDaoCollection;
import ar.com.aterrizar.entidades.Asiento;

public class BuscarAsientoWindow extends SimpleWindow<SearchAsientoByExample> {
	
	private static final long serialVersionUID = 1L;

	public static final String SEARCH_OLD = "search_old";
	
	public BuscarAsientoWindow(InicioWindow owner, SearchAsientoByExample model) {
		super(owner, model);
		AterrizarCom.getInstance().getAsientosAterrizar().setUsuario(owner.getUsuario());
	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {
		super.createMainTemplate(mainPanel);
		this.setTitle("Buscador de Asientos");
		this.setTaskDescription("Ingrese los parámetros de búsqueda");		
		this.createResultsGrid(mainPanel);
		this.createGridActions(mainPanel);
	}

	// ***********************************************************
	// ** Grid
	// ***********************************************************

	protected void createResultsGrid(Panel mainPanel) {
		Table<Asiento> table = new Table<Asiento>(mainPanel, this.getModelObject()
				.getEntityType());

		table.bindItemsToProperty(Search.RESULTS);
		table.bindSelection(Search.SELECTED);

		this.describeResultsGrid(table);
	}


	// ***********************************************************
	// ** Actions
	// ***********************************************************
	protected void describeResultsGrid(Table<Asiento> table) {
		Column<Asiento> aerolineaColumn = new Column<Asiento>(table);
		aerolineaColumn.setTitle("Aerolinea");
		aerolineaColumn.setFixedSize(150);
		aerolineaColumn.bindContentsToProperty("nombreDeAerolinea");
		
		Column<Asiento> origenColumn = new Column<Asiento>(table);
		origenColumn.setTitle("Origen");
		origenColumn.setFixedSize(150);
		origenColumn.bindContentsToProperty("origen");

		Column<Asiento> destinoColumn = new Column<Asiento>(table);
		destinoColumn.setTitle("Destino");
		destinoColumn.setFixedSize(150);
		destinoColumn.bindContentsToProperty("destino");
		
		Column<Asiento> fechaColumn = new Column<Asiento>(table);
		fechaColumn.setTitle("Fecha");
		fechaColumn.setFixedSize(150);
		fechaColumn.bindContentsToTransformer(new Transformer<Asiento, String>() {
			@Override
			public String transform(Asiento asiento) {
				return new SimpleDateFormat("dd/MM/yyyy").format(asiento.getVuelo().getFechaOrigen().getFecha());
			}
		});
		
		Column<Asiento> vueloColumn = new Column<Asiento>(table);
		vueloColumn.setTitle("Vuelo");
		vueloColumn.setFixedSize(150);
		vueloColumn.bindContentsToProperty("codigoDeVuelo");

		Column<Asiento> asientoColumn = new Column<Asiento>(table);
		asientoColumn.setTitle("asiento");
		asientoColumn.setFixedSize(150);
		asientoColumn.bindContentsToProperty("codigo");

		Column<Asiento> precioColumn = new Column<Asiento>(table);
		precioColumn.setTitle("precio");
		precioColumn.setFixedSize(150);
		precioColumn.bindContentsToProperty("precio");
		
		table.setHeigth(300);
		table.setWidth(1050);
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		Button buscar = new Button(actionsPanel);
		buscar.setCaption("Buscar");
		buscar.onClick(new MessageSend(this.getModelObject(), Search.SEARCH));
		buscar.setAsDefault();
		
		Button buscar_old = new Button(actionsPanel);
		buscar_old.setCaption("Busqueda anterior");
		buscar_old.onClick(new MessageSend(this, BuscarAsientoWindow.SEARCH_OLD));
		buscar_old.setAsDefault();
	}

	protected void createGridActions(Panel mainPanel) {
		Panel actionsPanel = new Panel(mainPanel);
		actionsPanel.setLayout(new HorizontalLayout());

		NotNullObservable elementSelected = new NotNullObservable(
				Search.SELECTED);

		Button comprar = new Button(actionsPanel);
		comprar.setCaption("Comprar");
		comprar.bindEnabled(elementSelected);
		comprar.onClick(new MessageSend(this.getModelObject(), "comprar"));

		Button reservar = new Button(actionsPanel);
		reservar.setCaption("Reservar");
		reservar.bindEnabled(new NotNullObservable(Search.SELECTED));
		reservar.onClick(new MessageSend(this.getModelObject(), "reservar"));

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
	
	public void search_old(){
		ShowBusquedaModel busquedaModelo = new ShowBusquedaModel(AterrizarCom.getInstance().getBusquedasRealizadas());
		new BusquedasWindow(this.getOwner(), busquedaModelo).open();
		//this.getModelObject().setExample(ShowBusquedaModel.BusquedaToAsiento(busquedaModelo.getSelected()));
		Asiento selected = ShowBusquedaModel.BusquedaToAsiento(busquedaModelo.getSelected());
		Asiento example = this.getModelObject().getExample();
		String oldOrigen = example.getOrigen();
		String oldDestino = example.getDestino();
		String oldFecha = example.getFecha();
		example.setOrigen(selected.getOrigen());
		example.setDestino(selected.getDestino());
		example.setFecha(selected.getFecha());
		this.getModelObject().search();
		example.setOrigen(oldOrigen);
		example.setDestino(oldDestino);
		example.setFecha(oldFecha);
	}


}
