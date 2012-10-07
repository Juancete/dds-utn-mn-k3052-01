package ar.com.aterrizar.arena;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Window;

import ar.com.aterrizar.entidades.Asiento;
import ar.com.aterrizar.modelo.Usuario;

public abstract class vistaWindow extends Window<Usuario> {
	
	public vistaWindow(InicioWindow owner, Usuario modelUsuario) {
		super(owner, modelUsuario);
	}



@Override
public void createContents(Panel mainPanel) {
	// TODO Auto-generated method stub
	this.setTitle("Aterrizar.com");
	mainPanel.setLayout(new VerticalLayout());
	this.setSecondTitle(mainPanel);
}

public void setSecondTitle(Panel mainPanel){
	InicioWindow owner = (InicioWindow)this.getOwner();
	new Label(mainPanel).setText(this.setAction() + owner.getUsuario().getNombre());
	
	Table<Asiento> table = new Table<Asiento>(this, null);
	
	Column<Asiento> salidaColumn = new Column<Asiento>(table);
	salidaColumn.setTitle("Salida");
	salidaColumn.setFixedSize(100);
	salidaColumn.bindContentsToProperty(null);//Asiento.TIPO);
	
	Column<Asiento> aerolineaColumn = new Column<Asiento>(table);
	aerolineaColumn.setTitle("Aerolinea");
	aerolineaColumn.setFixedSize(100);
	
	Column<Asiento> vueloColumn = new Column<Asiento>(table);
	vueloColumn.setTitle("Vuelo");
	vueloColumn.setFixedSize(100);

	Column<Asiento> asientoColumn = new Column<Asiento>(table);
	asientoColumn.setTitle("Asiento");
	asientoColumn.setFixedSize(100);
	
	Column<Asiento> precioColumn = new Column<Asiento>(table);
	precioColumn.setTitle("Precio");
	precioColumn.setFixedSize(100);
	
	table.setHeigth(300);
	table.setWidth(600);
	
	Button cerrar = new Button(mainPanel).setCaption("Cerrar");
	cerrar.onClick(new MessageSend(this, "close"));
}

public abstract String setAction();


}


