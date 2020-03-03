package com.example.springbootserialport;

import jssc.SerialPort;
import jssc.SerialPortException;
import org.springframework.stereotype.Component;

@Component
public class ModemConnection {
    private final SerialPort serialPort;

    private final byte newLine = 0x0D;
    private final byte endOfLine = 0x1A;

    public ModemConnection() throws SerialPortException, InterruptedException {
        this.serialPort = new SerialPort("COM3");
        serialPort.openPort();
        serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

        /*
        System.out.println(readTextMessage("0"));
        serialPort.writeString("AT+CGMI");
        serialPort.writeByte(newLine);

        Thread.sleep(1000);
        System.out.println(serialPort.readString());
         */
    }

    public void sendTextMessage(String phoneNumber, String message){
        try {
            serialPort.writeString("AT+CMGF=1");
            Thread.sleep(1000);
            serialPort.writeByte(newLine);

            serialPort.writeString("AT+CMGS=\"" + phoneNumber+ "\"");
            Thread.sleep(1000);
            serialPort.writeByte(newLine);
            Thread.sleep(1000);

            serialPort.writeString(message);
            Thread.sleep(1000);
            serialPort.writeByte(newLine);
            Thread.sleep(1000);
            serialPort.writeByte(endOfLine);

        } catch (SerialPortException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String readTextMessage(String memoryPlace){
        try {
            serialPort.writeString("AT+CMGF=1");
            Thread.sleep(1000);
            serialPort.writeByte(newLine);
            Thread.sleep(1000);

            serialPort.writeString("AT+CMGR=" + memoryPlace);
            Thread.sleep(1000);
            serialPort.writeByte(newLine);
            Thread.sleep(1000);

            return serialPort.readString();

        } catch (SerialPortException | InterruptedException e) {
            e.printStackTrace();
        }

        return "";
    }
}

