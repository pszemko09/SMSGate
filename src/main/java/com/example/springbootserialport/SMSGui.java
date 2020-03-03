package com.example.springbootserialport;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import jssc.SerialPortException;
import org.springframework.beans.factory.annotation.Autowired;


@Route
public class SMSGui extends VerticalLayout {
    private final TextField textFieldNumber;
    private final TextField textFieldMessage;
    private final Button sendMessageButton;

    private ModemConnection modemConnection;

    @Autowired
    public SMSGui(ModemConnection modemConnection) throws SerialPortException, InterruptedException {
        this.modemConnection = modemConnection;

        textFieldNumber = new TextField();
        textFieldMessage = new TextField();
        sendMessageButton = new Button("Send Message");

        add(textFieldNumber, textFieldMessage, sendMessageButton);

        sendMessageButton.addClickListener(clickEvent -> {
            modemConnection.sendTextMessage(textFieldNumber.getValue(), textFieldMessage.getValue());
        });
    }
}
