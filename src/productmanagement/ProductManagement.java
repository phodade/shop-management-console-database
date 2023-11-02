package productmanagement;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import db_operation.DBUtils;


public class ProductManagement 
{

	public static void productManagement()throws IOException
	{

		Scanner scanner = new Scanner(System.in); 

		boolean canIKeepRunningTheProgram = true; 
		while (canIKeepRunningTheProgram == true) 
		{ 
			System.out.println("**** Welcome to Product Management *****");
			System.out.println("\n");
			System.out.println("What would you like to do ?");
			System.out.println("1. Add Product");
			System.out.println("2. Search Product");
			System.out.println("3. Edit Product");
			System.out.println("4. Delete Product");
			System.out.println("5. Quit");

			int optionSelectedByUser = scanner.nextInt();

			if (optionSelectedByUser == ProductOptions.QUIT)
			{
				System.out.println("!!! Program closed");
				canIKeepRunningTheProgram = false;

			}
			else if (optionSelectedByUser == ProductOptions.ADD_Product) 
			{
				addUser();
				System.out.println("\n");
			} 
			else if (optionSelectedByUser == ProductOptions.SEARCH_Product)
			{
				System.out.print("Enter User Name to search: ");
				scanner.nextLine(); 
				String sn = scanner.nextLine();
				searchUser(sn);
				System.out.println("\n");
			} 
			else if (optionSelectedByUser == ProductOptions.DELETE_Product) 
			{
				System.out.print("Enter User Name to delete: ");
				scanner.nextLine(); 
				String deleteUserName = scanner.nextLine();
				deleteUser(deleteUserName);
				System.out.println("\n");
			} 
			else if (optionSelectedByUser == ProductOptions.EDIT_Product)
			{
				System.out.print("Enter User Name to edit: ");
				scanner.nextLine(); 
				String editUserName = scanner.nextLine();
				editUser(editUserName);
				System.out.println("\n");
			}

		}
		System.out.println("\n");
	}


	public static void addUser()
	{
		Scanner scanner = new Scanner(System.in);

		Product productObject = new Product(); 

		System.out.print(" Name: ");
		productObject.name = scanner.nextLine();

		System.out.print("Id : ");
		productObject.id = scanner.nextLine();

		System.out.print("category: ");
		productObject.category = scanner.nextLine();

		System.out.print("quantity : ");
		productObject.quantity = scanner.nextLine();

		System.out.print("price: ");
		productObject.price = scanner.nextLine();

		System.out.println(" Name: " + productObject.name);
		System.out.println("Id: " + productObject.id);
		System.out.println("category: " + productObject.category);
		System.out.println(" quantity: " + productObject.quantity);
		System.out.println("price: " + productObject.price);

		String query = "insert into product(Name, Id, category, quantity, price) values ('"+ productObject.name + "', '" + productObject.id + "', '" + productObject.category + "','"+ productObject.quantity + "','" + productObject.price + "')";

		DBUtils.excuteQuery(query);

	}

	public static void searchUser(String name) 
	{

		String query = "select * from product where name='" + name + "' ";

		ResultSet rs = DBUtils.executeQueryGetResult(query);

		try {
			while (rs.next())
			
			{ 
				if (rs.getString("name").equalsIgnoreCase(name))
				{
					System.out.println("name: " + rs.getString("name"));
					System.out.println("id: " + rs.getString("id"));
					System.out.println("category: " + rs.getString("category"));
					System.out.println(" price: " + rs.getString("price"));
				
					return;
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("User not found.");
		}

	}


	public static void deleteUser(String name) 
	{
		String query = "delete from product where name='" + name + "' ";
		DBUtils.excuteQuery(query);
	}


	public static void editUser(String name)
	{
		
		String query = "select * from product where name='" + name + "' ";
		ResultSet rs =DBUtils.executeQueryGetResult(query);
		
		try {
			while (rs.next())
			{ 
				if (rs.getString("User_Name").equalsIgnoreCase(name)) 
				{
					Scanner scanner = new Scanner(System.in);
					Product product = new Product();
					
					System.out.println("Editing user: " + product.name);

					System.out.print("New User Name: ");
					product.name = scanner.nextLine();

					System.out.print("New Login Name: ");
					product.id = scanner.nextLine();

					System.out.print("New Password: ");
					product.category = scanner.nextLine();

					System.out.print("New confirmPassword: ");
					product.quantity = scanner.nextLine();

					System.out.print("New User Role: ");
					product.price = scanner.nextLine();
					
					String updateQuery = "update product set "+ "name='"+product.name+"', id = '"+product.id+"', "+ "category='"+product.category+"', quantity='"+product.quantity+"', "+ "price='"+product.price+"' where productid='"+rs.getString("productid")+"'";
					
					DBUtils.excuteQuery(updateQuery);

					System.out.println("User information updated.");

					return;
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("User not found.");
		}

		
	}

}