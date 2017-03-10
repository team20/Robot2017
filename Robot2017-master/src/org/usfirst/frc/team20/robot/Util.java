//Author: Rahul Shah
package org.usfirst.frc.team20.robot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Util {
	File file;
	FileWriter printWriter;

	// log file function
	public void createFile(String fileName) throws IOException {
		file = new File("/home/lvuser/" + fileName);
		file.createNewFile();
		printWriter = new FileWriter(file);
		printWriter.write("new lob file\n");
	}

	public void WriteToFile(String data) throws IOException {
		printWriter.write(data);
	}

	public void Closefile() throws IOException {
		printWriter.flush();
		printWriter.close();

	}

	public String getCameraAngle() {
		String angleDistance = "";
		try {
			System.out.println("*******Trying to turn angle");
			angleDistance = readSocket("10.0.20.79", 9999, "009");
			System.out.println("*******Turned angle  ********** = " + angleDistance);
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

		return angleDistance;
	}

	public String readSocket(String ipAddress, int Port, String sentence) throws IOException {
		String socketString = "-1";
		DatagramSocket clientSocket;
		try {
			clientSocket = new DatagramSocket();
			InetAddress IPAddress = InetAddress.getByName(ipAddress);
			byte[] sendData = new byte[1024];
			byte[] receiveData = new byte[1024];
			System.out.println("sentence = " + sentence);
			sendData = sentence.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);

			clientSocket.send(sendPacket);
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);
			String modifiedSentence = new String(receivePacket.getData());
			System.out.println("FROM SERVER:" + modifiedSentence);
			socketString = modifiedSentence;
			clientSocket.close();
			System.out.println("done");
		} catch (SocketException e) {
			System.out.println("Socket error: " + e.toString());
		}

		return socketString;
	}
}
