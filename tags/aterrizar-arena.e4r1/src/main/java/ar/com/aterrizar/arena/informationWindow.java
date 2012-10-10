package ar.com.aterrizar.arena;

import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class informationWindow extends SimpleWindow {
	
	protected String information;
	
	public informationWindow(WindowOwner owner,Object model,String i) {
		super(owner, model);
		information = i;
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void createContents(Panel mainPanel) {
		this.setTitle("aterrizar.com");
		Label informationLabel = new Label(mainPanel);
		informationLabel.setText(information);
		
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		// TODO Auto-generated method stub
		
	}
	
}
