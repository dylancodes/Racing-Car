import java.net.*;
import java.util.*;
import java.io.*;

public class carServer {
	public static void main(String[] args) {
		Car[] garage = new Car[5];
		for(int i = 0; i < garage.length -1 ; i++) {
			garage[i] = new Car(50);
		}
		double newResponse;
		final int PORT = 8000;
		try {
			ServerSocket server = new ServerSocket(PORT);
			System.out.println("Server listening on port: " + PORT);
			while(true) {
				Socket client = server.accept();
				InputStream instream = client.getInputStream();
				Scanner request = new Scanner(instream);
				OutputStream outstream = client.getOutputStream();
				PrintWriter response = new PrintWriter(outstream);
				
				// Manipulate Request Data
				if(request.hasNextLine()) {
					System.out.println("Request Received");
				}
				String req = request.nextLine();
				String[] requestData = req.split(",");
				if(requestData[0].equals("A")) {
					int carId = Integer.parseInt(requestData[1]);
					double change = Double.parseDouble(requestData[2]);
					garage[carId].addGas(change);
					newResponse = garage[carId].getGas();
				}
				else if(requestData[0].equals("B")) {
					int carId = Integer.parseInt(requestData[1]);
					double change = Double.parseDouble(requestData[2]);
					garage[carId].drive(change);
					newResponse = garage[carId].getGas();
				}
				else {
					int carId = Integer.parseInt(requestData[1]);
					newResponse = garage[carId].getGas();
				}
				
				String finalResponse = Double.toString(newResponse);
				
				// Send Response to Client
				response.println(finalResponse);
				response.flush();
				System.out.println("Response Sent");
				client.close();
			}
		} catch(IOException error) {
			System.err.println(error);
		}
		
	}

}
