package project21;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class myDatabase {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lab02", "root", "");
		java.sql.Statement statement = connection.createStatement();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
//		statement.executeUpdate("create table Staff ("
//				+ "id char(4) not null,"
//				+ "lastName varchar(15),"
//				+ "firstName varchar(15),"
//				+ "telephone char(10),"
//				+ "email varchar(40),"
//				+ "primary key (id))");
		System.out.print("0.view(all)  1.view  2.insert  3.update  4.exit\n你的选择：");
		java.util.Scanner input = new java.util.Scanner(System.in);
		int choice = input.nextInt();
		while (choice != 4) {
			if (choice == 0) {
				preparedStatement = connection.prepareStatement("select * from lab02.staff");
				resultSet = preparedStatement.executeQuery();
				resultSet = preparedStatement.executeQuery();
				System.out.println("ID\tlastName\tfirstName\ttelephone\temail");
				while (resultSet.next()) {
					System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t\t" 
							+ resultSet.getString(3) + "\t\t" + resultSet.getString(4) + "\t\t" + resultSet.getString(5));
				}
			}
			else if (choice == 1) {
				System.out.print("请输入要查询的id：");
				int id = input.nextInt();
				preparedStatement = connection.prepareStatement("select * from lab02.staff where id = ?");
				preparedStatement.setInt(1, id);
				resultSet = preparedStatement.executeQuery();
				if (!resultSet.next()) {
					System.out.println("该id不存在！");
				} else {
					resultSet = preparedStatement.executeQuery();
					System.out.println("ID\tlastName\tfirstName\ttelephone\temail");
					while (resultSet.next()) {
						System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t\t" 
								+ resultSet.getString(3) + "\t\t" + resultSet.getString(4) + "\t\t" + resultSet.getString(5));
					}
				}
			}
			else if (choice == 2) {
				System.out.print("请输入要插入的信息：ID：");
				int id = input.nextInt();
				preparedStatement = connection.prepareStatement("select id from lab02.staff where id = ?");
				preparedStatement.setInt(1, id);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {//不为空则说明该id已存在，重新输入
					System.out.println("该id已存在！请重新输入ID：");
					id = input.nextInt();
					preparedStatement = connection.prepareStatement("select id from lab02.staff where id = ?");
					preparedStatement.setInt(1, id);
					resultSet = preparedStatement.executeQuery();
				}
				System.out.print("lastName：");
				String lastName = input.next();
				System.out.print("firstName：");
				String firstName = input.next();
				System.out.print("telephone：");
				String telephone = input.next();
				System.out.print("email：");
				String email = input.next();
				preparedStatement = connection.prepareStatement("insert into staff(id, lastName, firstName, telephone, email) values (?, ?, ?, ?, ?)");
				preparedStatement.setInt(1, id);
				preparedStatement.setString(2, lastName);
				preparedStatement.setString(3, firstName);
				preparedStatement.setString(4, telephone);
				preparedStatement.setString(5, email);
				preparedStatement.execute();
				System.out.println("插入成功！");
			}
			else if (choice == 3) {
				System.out.print("请输入修改目标的id：");
				int id = input.nextInt();
				preparedStatement = connection.prepareStatement("select * from lab02.staff where id = ?");
				preparedStatement.setInt(1, id);
				resultSet = preparedStatement.executeQuery();
				if (!resultSet.next()) {
					System.out.println("该id不存在！");
				}else {
					System.out.print("1.ID  2.lastName  3.firstName  4.telephone  5.email  6.退出修改\n你的选择：");
					int ch = input.nextInt();
					while (ch != 6) {
						if (ch == 1) {
							System.out.print("请输入修改后的id：");
							int temp_id = input.nextInt();
							preparedStatement = connection.prepareStatement("update staff set id = ? where id = ?");
							preparedStatement.setInt(1, temp_id);
							preparedStatement.setInt(2, id);
							preparedStatement.execute();
							System.out.println("修改成功！！");
						}
						else if (ch == 2) {
							System.out.print("请输入修改后的lastName：");
							String temp_lastName = input.next();
							preparedStatement = connection.prepareStatement("update staff set lastName = ? where id = ?");
							preparedStatement.setString(1, temp_lastName);
							preparedStatement.setInt(2, id);
							preparedStatement.execute();
							System.out.println("修改成功！！");
						}
						else if (ch == 3) {
							System.out.print("请输入修改后的firstName：");
							String temp_firstName = input.next();
							preparedStatement = connection.prepareStatement("update staff set firstName = ? where id = ?");
							preparedStatement.setString(1, temp_firstName);
							preparedStatement.setInt(2, id);
							preparedStatement.execute();
							System.out.println("修改成功！！");
						}
						else if (ch == 4) {
							System.out.print("请输入修改后的telephone：");
							int temp_telephone = input.nextInt();
							preparedStatement = connection.prepareStatement("update staff set telephone = ? where id = ?");
							preparedStatement.setInt(1, temp_telephone);
							preparedStatement.setInt(2, id);
							preparedStatement.execute();
							System.out.println("修改成功！！");
						}
						else if (ch == 5) {
							System.out.print("请输入修改后的email：");
							String temp_email = input.next();
							preparedStatement = connection.prepareStatement("update staff set email = ? where id = ?");
							preparedStatement.setString(1, temp_email);
							preparedStatement.setInt(2, id);
							preparedStatement.execute();
							System.out.println("修改成功！！");
						}
						System.out.print("1.ID  2.lastName  3.firstName  4.telephone  5.email  6.退出修改\n你的选择：");
						ch = input.nextInt();
					}
				}
			}
			System.out.println();
			System.out.println("0.view(all)  1.view  2.insert  3.update  4.exit\n你的选择：");
			choice = input.nextInt();
		}
		System.out.println("bye~");
		input.close();
		resultSet.close();
		preparedStatement.close();
		connection.close();
	}

}
