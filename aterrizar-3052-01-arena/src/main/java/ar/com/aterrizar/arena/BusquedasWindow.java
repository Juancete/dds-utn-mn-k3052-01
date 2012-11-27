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
import org.uqbar.arena.windows.WindowOwner;

import com.uqbar.commons.collections.Transformer;

import ar.com.aterrizar.commons.model.ShowModel;
import ar.com.aterrizar.daos.BusquedaDaoCollection;
import ar.com.aterrizar.modelo.Busqueda;

public class BusquedasWindow extends Window<ShowModel<?>> {

	private static final long serialVersionUID = 1L;
	public BusquedasWindow(WindowOwner owner, ShowModel<Busqueda> showModel) {
		super(owner, showModel);
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
		new Label(mainPanel).setText("Busquedas para "
				+ owner.getUsuario().getNombre());
	}
	
	public void createGrid(Panel mainPanel) {
		Table<Busqueda> table = new Table<Busqueda>(mainPanel, Busqueda.class);

		table.bindItemsToProperty(ShowModel.RESULTS);
		table.bindSelection(ShowModel.SELECTED);
		
		Column<Busqueda> origenColumn = new Column<Busqueda>(table);
		origenColumn.setTitle("Origen");
		origenColumn.setFixedSize(100);
		origenColumn
				.bindContentsToProperty("origen");
		
		Column<Busqueda> destinoColumn = new Column<Busqueda>(table);
		destinoColumn.setTitle("Destino");
		destinoColumn.setFixedSize(100);
		destinoColumn
				.bindContentsToProperty("destino");

		Column<Busqueda> fechaColumn = new Column<Busqueda>(table);
		fechaColumn.setTitle("Fecha");
		fechaColumn.setFixedSize(100);
		fechaColumn
			.bindContentsToTransformer(new Transformer<Busqueda, String>() {
			@Override
			public String transform(Busqueda unaBusqueda) {
				if (unaBusqueda.getFecha() != null){
				return new SimpleDateFormat("dd/MM/yyyy").format(
						unaBusqueda.getFecha().getFecha());}
				else
				{return "";}
			}
		});
		
		table.setHeigth(300);
		table.setWidth(300);
	}
	private void addActions(Panel mainPanel) {

		Button cerrar = new Button(mainPanel).setCaption("Cerrar");
		cerrar.onClick(new MessageSend(this, "close"));
	}
}
