package ar.com.aterrizar.arena;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.commons.model.Search;

import ar.com.aterrizar.entidades.Asiento;
import ar.com.aterrizar.modelo.state.AsientoYaReservadoException;

public abstract class SearchAsientoWindow<E, T extends Search<E>> extends SimpleWindow<T> {
	private static final long serialVersionUID = 1L;

	public SearchAsientoWindow(WindowOwner owner, T model) {
		super(owner, model);
	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {
		super.createMainTemplate(mainPanel);
		this.createResultsGrid(mainPanel);
		this.createGridActions(mainPanel);
	}

	// ***********************************************************
	// ** Grid
	// ***********************************************************

	protected void createResultsGrid(Panel mainPanel) {
		Table<E> table = new Table<E>(mainPanel, this.getModelObject().getEntityType());

		table.bindItemsToProperty(Search.RESULTS);
		table.bindSelection(Search.SELECTED);

		this.describeResultsGrid(table);
	}

	protected abstract void describeResultsGrid(Table<E> builder);

	// ***********************************************************
	// ** Actions
	// ***********************************************************

	@Override
	protected void addActions(Panel actionsPanel) {
		Button buscar = new Button(actionsPanel);
		buscar.setCaption("Buscar");
		buscar.onClick(new MessageSend(this.getModelObject(), Search.SEARCH));
		buscar.setAsDefault();
		
		// TODO Ver si agregamos la acción de limpiar:
		// new Button(actions).setCaption("Limpiar").onClick(new MessageSend(this.getModel(), "clear"));
	}

	protected void createGridActions(Panel mainPanel) {
		Panel actionsPanel = new Panel(mainPanel);
		actionsPanel.setLayout(new HorizontalLayout());

		NotNullObservable elementSelected = new NotNullObservable(Search.SELECTED);

		Button comprar = new Button(actionsPanel);
		comprar.setCaption("Comprar");
		comprar.bindEnabled(elementSelected);
		comprar.onClick(new MessageSend(this.getModelObject(), "comprar"));
	
		
		//TODO agregar manejo de errores !
		
		Button reservar = new Button(actionsPanel);
		reservar.setCaption("Reservar");
		reservar.bindEnabled(new NotNullObservable(Search.SELECTED));
		reservar.onClick(new MessageSend(this.getModelObject(), "reservar"));

	}
	
	

}

