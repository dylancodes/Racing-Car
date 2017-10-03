import java.util.*;
import java.net.*;
import java.io.*;

public class carClient {

	public static void main(String[] args) {
		final int PORT = 8000;
		String action;
		String car;
		String change;
		String requestData;
		try {
			Socket app = new Socket("127.0.0.1", PORT);
			InputStream instream = app.getInputStream();
			OutputStream outstream = app.getOutputStream();
			Scanner response = new Scanner(instream);
			PrintWriter request = new PrintWriter(outstream);
			
			// User Interface
			Scanner userInput = new Scanner(System.in);
			System.out.println("Start your engines!");
			System.out.println("Select an action: \n(A) Add Gas \n(B) Drive \n(C) Get Gas");
			action = userInput.next();
			if(action.equals("A")) {
				System.out.println("Which car would you like to add gas to? (0-5)");
				car = userInput.next();
				System.out.println("How much gas should we add?");
				change = userInput.next();
				requestData = action + "," + car + "," + change;
			}
			else if(action.equals("B")) {
				System.out.println("Which car would you like to drive? (0-5)");
				car = userInput.next();
				System.out.println("How far should we drive?");
				change = userInput.next();
				requestData = action + "," + car + "," + change;
			}
			else {
				System.out.println("Which gas tank would you like to see?");
				car = userInput.next();
				requestData = action + "," + car;
			}
			
			// Send request data to server
			request.println(requestData);
			request.flush();
			
			// Process response from server
			while(response.hasNext()) {
				String responseData = response.nextLine();
				System.out.println(responseData + " mpg in Car " + car);
			}
			
			app.close();
		}
		catch(Exception error) {
			System.out.println(error);
		}

	}

}
