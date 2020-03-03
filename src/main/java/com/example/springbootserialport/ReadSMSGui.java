package com.example.springbootserialport;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import jssc.SerialPortException;
import org.springframework.beans.factory.annotation.Autowired;


@Route
public class ReadSMSGui extends VerticalLayout {
    private final TextField textFieldMemoryPlace;
    private final Button readMessageButton;
    private final Label messageLabel;

    private ModemConnection modemConnection;

    @Autowired
    public ReadSMSGui(ModemConnection modemConnection) throws SerialPortException, InterruptedException {
        this.modemConnection = modemConnection;
        textFieldMemoryPlace = new TextField();
        readMessageButton = new Button("Read Message");
        messageLabel = new Label();

        add(textFieldMemoryPlace, readMessageButton, messageLabel);

        readMessageButton.addClickListener(clickEvent -> {
            String message = modemConnection.readTextMessage(textFieldMemoryPlace.getValue());
            messageLabel.setText(message);
        });
    }
}
