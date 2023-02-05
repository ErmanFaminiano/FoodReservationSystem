
import java.io.Console;
import java.sql.*;
import java.util.*;

public class FRSystem {
	static int select;
	static Console cons = System.console();
	static void adminManagement(Connection conn, Scanner scan) throws SQLException {
		System.out.println("\t\t=======================================");
		System.out.println("\t\t=\t\tADMIN                 =");
		System.out.println("\t\t=======================================");
		System.out.println("\t\t|       [1] Item Manage               |");
		System.out.println("\t\t|       [2] Manage User               |");
		System.out.println("\t\t|       [3] GO BACK                   |");
		System.out.println("\t\t=======================================");
		System.out.print("\t\tSelect input: ");
		select = scan.nextInt();
		switch(select) {
		case 1:
			itemManage(conn, scan, select);
			break;
			
		case 2:
			ManageUser(conn, scan);
			break;
		
		case 3:
			admin(conn, scan);
			break;
		
		}
	}
	static void AddCart(Connection conn, Scanner scan) throws SQLException{
		int productID, quantity;
		Statement statement = conn.createStatement();
		ResultSet output = statement.executeQuery("SELECT * FROM  Items");
		System.out.println("\t\t [B] GO BACK");
		System.out.println("\t\t===========================================");
		System.out.println("\t\t| ID  \t    PRODUCT  \t   PRICE   STOCK  =");
		System.out.println("\t\t===========================================");
		while(output.next()) {
			System.out.println("\t\t| " + output.getInt("ID")+ "\t" +output.getString("Product") + "\t    " + output.getInt("Price") + "\t   " + output.getInt("Stock") );	
		}
		System.out.print("\t\tEnter Product ID: ");
		productID = scan.nextInt();
		System.out.print("\t\tEnter quantity: ");
		quantity = scan.nextInt();		
		 String sql = "SELECT * FROM Items WHERE id = " + productID;
		ResultSet result = statement.executeQuery(sql);
		if(result.next()) {
		String Product = result.getString("Product");
		int Price = result.getInt("Price");
		int Stock = result.getInt("Stock");
		PreparedStatement st = conn
				.prepareStatement("INSERT INTO ViewCart (Product, Price,Stock, Quantity) VALUES (?,?,?,?)");

		st.setString(1, Product);
		st.setInt(2, Price);
		st.setInt(3, Stock);
		st.setInt(4, quantity);
		
		if (st.executeUpdate() != 0) {
			System.out.println("\t\tProduct added successfully to cart.");
		} else {
			System.out.println("Failed to add Product");
		}

		System.out.println("");

	}
		else {
			System.out.println("Try again!");
		}
		} 
	
	
	static void ViewCart(Connection conn, Scanner scan) throws SQLException{
		Statement statement = conn.createStatement();
		ResultSet output = statement.executeQuery("SELECT * FROM  ViewCart");
		System.out.println("\t\t=====================================================");
		System.out.println("\t\t| ID  \t    PRODUCT  \t   PRICE   STOCK   QUANTITY =");
		System.out.println("\t\t=====================================================");
		while(output.next()) {
			System.out.println("\t\t| " + output.getInt("ID")+ "\t" +output.getString("Product") + "\t    " + output.getInt("Price") + "\t   " + output.getInt("Stock") + "\t" + output.getInt("Quantity"));	
			
		}
		System.out.print("");
		System.out.println("\t\t===========================================");
		System.out.println("\t\t[1] GO BACK                               =");
		System.out.println("\t\t===========================================");
		int choice = scan.nextInt();
		switch (choice) {
		case 1:
			userManagement(conn, scan);
			break;
		default:
			
			System.out.println("Invalid input!");		
			
		}
	}
	
	static void PlaceOrder(Connection conn, Scanner scan) throws SQLException{
		int choice;
		Statement statement = conn.createStatement();
		ResultSet output = statement.executeQuery("SELECT * FROM  ViewCart");
		System.out.println("\t\t=====================================================");
		System.out.println("\t\t| ID  \t    PRODUCT  \t   PRICE   STOCK   QUANTITY =");
		System.out.println("\t\t=====================================================");
		while(output.next()) {
			System.out.println("\t\t| " + output.getInt("ID")+ "\t" +output.getString("Product") + "\t    " + output.getInt("Price") + "\t   " + output.getInt("Stock") + "\t" + output.getInt("Quantity"));	
			
		}
		System.out.println("\t\t [1] GO BACK");
		System.out.println("\t\t [2] ");
		System.out.print("\t Select input: ");
		choice = scan.nextInt();
		switch(choice) {
		case 1:
			
			
			break;
		}
		
		
	}
	static void OrderCancel(Connection conn, Scanner scan) throws SQLException{
		
	}
	static void userManagement(Connection conn, Scanner scan) throws SQLException {
		System.out.println("\t\t=======================================");
		System.out.println("\t\t=\t\tUSER                  =");
		System.out.println("\t\t=======================================");
		System.out.println("\t\t|       [1] Add Cart                  |");
		System.out.println("\t\t|       [2] View Cart                 |");
		System.out.println("\t\t|       [3] Place Order               |");
		System.out.println("\t\t|       [4] Order Cancel              |");
		System.out.println("\t\t|       [5] Go Back                   |");
		System.out.println("\t\t=======================================");
		System.out.print("\t\tSelect input: ");
		select = scan.nextInt();
		do {
		switch(select) {
		case 1:
			AddCart(conn, scan);
			break;
			
		case 2:
			ViewCart(conn, scan);
			break;
		case 3:
			PlaceOrder(conn, scan);
			break;
		case 4:
			OrderCancel(conn, scan);
			break;
		case 5: 
			
			break;
			
		}
		}
		while(select != 5);
		
		
		}
	static void itemManage(Connection conn, Scanner scan, int Select) throws SQLException {

		do {
			Statement statement = conn.createStatement();
			ResultSet output = statement.executeQuery("SELECT * FROM  Items");
			System.out.println("\t\t===========================================");
			System.out.println("\t\t| ID  \t    PRODUCT  \t   PRICE   STOCK  =");
			System.out.println("\t\t===========================================");
			while(output.next()) {
				System.out.println("\t\t| " + output.getInt("ID")+ "\t" +output.getString("Product") + "\t    " + output.getInt("Price") + "\t   " + output.getInt("Stock") + "");
			}
			System.out.println("\t\t==========================================");
			System.out.println("\t\t==========================================");
			System.out.println("\t\t|          [1] Add Product               |");
			System.out.println("\t\t|          [2] Modify Product            |");
			System.out.println("\t\t|          [3] Delete Product            |");
			System.out.println("\t\t|          [4] Go Back                   |");
			System.out.println("\t\t==========================================");
			System.out.print("\t\t Select input: ");
			select = scan.nextInt();
			switch (select) {
			case 1:
				addProduct(conn, scan);
				break;
			case 2:
				updateProduct(conn, scan);
				break;
			case 3:
				deleteProduct(conn, scan);
				break;
			case 4:
				adminManagement(conn, scan);
				break;
				
				
			}
		}while(select != 4);
	}
	static void ManageOrder(Connection conn, Scanner scan) throws SQLException{
		
	}
	static void deleteUser(Connection conn,Scanner scan) throws SQLException {
		int id;

		System.out.print("\t\tEnter the id of product you want to delete: ");
		id = scan.nextInt();

		PreparedStatement statement = conn.prepareStatement("DELETE from UserInfo WHERE id = ?");
		statement.setInt(1, id);

		if (statement.executeUpdate() != 0) {
			System.out.println("Product deleted successfully");
		} else {
			System.out.println("Failed to delete product");
		}

		System.out.println("");
	}
	static void modifyUser(Connection conn, Scanner scan) throws SQLException {
		int id;
		String first_name, last_name, username, password;
		System.out.print("Enter the id of user you want to update: ");
		id = scan.nextInt();

		System.out.print("Enter first name: ");
		first_name = scan.next();

		System.out.print("Enter last name: ");
		last_name = scan.next();

		System.out.print("Enter username: ");
		username = scan.next();
		
		System.out.print("Enter password: ");
		password = scan.next();
		


		PreparedStatement statement = conn
				.prepareStatement("UPDATE UserInfo SET first_name= ?, last_name= ?, username= ?, password= ? where id= ?");
		statement.setString(1, first_name);
		statement.setString(2, last_name);
		statement.setString(3, username);
		statement.setString(4, password);
		statement.setInt(5, id);

		if (statement.executeUpdate() != 0) {
			System.out.println("Product updated successfully");
		} else {
			System.out.println("Failed to update product");
		}

		System.out.println("");
		
	}
	static void PaymentManage(Connection conn, Scanner scan) throws SQLException{
		
	}
	static void ManageUser(Connection conn, Scanner scan) throws SQLException{
		Statement statement = conn.createStatement();
		ResultSet output = statement.executeQuery("SELECT * FROM  UserInfo");
		System.out.println("\t\t=================================================================");
		System.out.println("\t\t| ID  \t    FIRSTNAME \t  LASTNAME      USERNAME      PASSWORD  =");
		System.out.println("\t\t=================================================================");
		while(output.next()) {
			System.out.println("\t\t| " + output.getInt("ID")+ "\t     " +output.getString("first_name") + "\t   " + output.getString("last_name") + "\t" + output.getString("username") + output.getString(5) + "\t" + output.getString("password"));
		}
		do {
		System.out.println("\t\t==========================================");
		System.out.println("\t\t|          [1] Modify User               |");
		System.out.println("\t\t|          [2] Delete User               |");
		System.out.println("\t\t|          [3] Go Back                   |");
		System.out.println("\t\t==========================================");
		System.out.print("\t\t Select input: ");
		select = scan.nextInt();
		switch (select) {
		case 1:
			modifyUser(conn, scan);
			break;
		case 2:
			deleteUser(conn, scan);
			break;
		case 3:
			adminManagement(conn, scan);
			break;
		}
	}while(select != 4);
		
	}
	
	
	static void admin(Connection conn, Scanner scan) throws SQLException {
		Statement statement = conn.createStatement();
		
		String username, pass;
		
		System.out.println("\t\t=====================================");
		System.out.println("\t\t\t      ADMIN LOGIN");
		System.out.println("\t\t=====================================");
		System.out.print("\t\tUsername: ");
		username = scan.next();
		System.out.print("\t\tPassword: ");
		pass = scan.next();
		ResultSet output = statement.executeQuery("SELECT * from Admininfo WHERE username = '" + username + "' AND password = '" + pass+ "'");

		System.out.println("");
		if (output.next()) {
			System.out.println("\t\t=====================================");
			System.out.println("\t\t Successfully logged in!");
			System.out.println("\t\t=====================================\n\n");
			adminManagement(conn, scan);
			
		}
		else {
			
			System.exit(0);
		}
	}
	static void user(Connection conn, Scanner scan) throws SQLException {
Statement statement = conn.createStatement();
		
		String username, pass;
	
		System.out.println("\t\t=====================================");
		System.out.println("\t\t\t      USER LOGIN");
		System.out.println("\t\t=====================================");
		System.out.print("\t\tUsername: ");
		username = scan.next();
		System.out.print("\t\tPassword: ");
		pass = scan.next();
		ResultSet output = statement.executeQuery("SELECT * from Userinfo WHERE username = '" + username + "' AND password = '" + pass+ "'");
		
		System.out.println("");
		if (output.next()) {
			System.out.println("\t\t=====================================");
			System.out.println("\t\t Successfully logged in!");
			System.out.println("\t\t=====================================\n\n");
			
			userManagement(conn, scan);
			
		}
		else {
			
			System.exit(0);
		}
	}

	static void addProduct(Connection conn, Scanner scan) throws SQLException {
		String Product;
		int Price, Stock;

		System.out.print("\t\t Enter Product: ");
		Product = scan.next();

		System.out.print("\t\t Enter Price: ");
		Price = scan.nextInt();

		System.out.print("\t\t Enter Stock: ");
		Stock = scan.nextInt();

		

		PreparedStatement statement = conn
				.prepareStatement("INSERT INTO Items (Product, Price,Stock) VALUES (?,?,?)");

		statement.setString(1, Product);
		statement.setInt(2, Price);
		statement.setInt(3, Stock);
		

		if (statement.executeUpdate() != 0) {
			System.out.println("\t\t =========================");
			System.out.println("\t  Product added successfully.");
			System.out.println("\t\t =========================");
		} else {
			System.out.println("Failed to add Product");
		}

		System.out.println("");
	}

	static void updateProduct(Connection conn, Scanner scan) throws SQLException {
		int id, Price, Stock;
		String Product;

		System.out.print("Enter the id of user you want to update: ");
		id = scan.nextInt();

		System.out.print("Enter product: ");
		Product = scan.next();

		System.out.print("Enter price: ");
		Price = scan.nextInt();

		System.out.print("Enter stock: ");
		Stock = scan.nextInt();


		PreparedStatement statement = conn
				.prepareStatement("UPDATE Items SET Product= ?, Price= ?,Stock= ? where id= ?");
		statement.setString(1, Product);
		statement.setInt(2, Price);
		statement.setInt(3, Stock);
		statement.setInt(4, id);

		if (statement.executeUpdate() != 0) {
			System.out.println("Product updated successfully");
		} else {
			System.out.println("Failed to update product");
		}

		System.out.println("");
	}

	static void deleteProduct(Connection conn, Scanner scan) throws SQLException {
		int id;

		System.out.print("\t\tEnter the id of product you want to delete: ");
		id = scan.nextInt();

		PreparedStatement statement = conn.prepareStatement("DELETE from Items WHERE id = ?");
		statement.setInt(1, id);

		if (statement.executeUpdate() != 0) {
			System.out.println("Product deleted successfully");
		} else {
			System.out.println("Failed to delete product");
		}

		System.out.println("");
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String URL = "jdbc:ucanaccess://FoodReservation_DB.accdb";

		try {
			// class variable name connection`
			Connection connection = DriverManager.getConnection(URL);

	

			// for our login credentials
			System.out.println("\n\n\t\t======================================");
			System.out.println("\t\t|            SELECT USERS            |");
			System.out.println("\t\t======================================");
			System.out.println("\t\t|             [1] ADMIN              |");
			System.out.println("\t\t|             [2] USER               |");
			System.out.println("\t\t======================================");
			int input;
			do {
			
			System.out.print("\t\t Select input: ");
			input = scan.nextInt();
			switch(input) {
			case 1:
				admin(connection, scan);
				break;
			case 2:
				user(connection, scan);
				break;
			case 3:
				System.out.println("\t\t======================================");
				System.out.println("\t\t\t    THANKS GOODBYE");
				System.out.println("\t\t======================================");
				break;
				
				default:	
					
			}
			} while (input != 3);
			
	

		} catch (SQLException e) {
			e.printStackTrace();
		}
		scan.close();
	}
}
