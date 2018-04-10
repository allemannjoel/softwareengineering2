package ch.fhnw.richards.lecture11_chatLab.v2_client;

public class Controller {
	private Model model;
	private View view;
	
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		
		view.btnConnect.setOnAction( event -> {
			view.btnConnect.setDisable(true);
			String ipAddress = view.txtIpAddress.getText();
			int port = Integer.parseInt(view.txtPort.getText());
			String name = view.txtName.getText();
			model.connect(ipAddress, port, name);
		});
		
		view.stage.setOnCloseRequest( event -> model.disconnect() );
		
		view.btnSend.setOnAction( event -> model.sendMessage(view.txtChatMessage.getText()));
		
		model.newestMessage.addListener( (o, oldValue, newValue) -> view.txtChatArea.appendText(newValue) );
	}
}
