/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;

/**
 *
 * @author Jiaying Qiu
 */
public class UserDAOImpl extends UserDAO {

    @Override
    public User getUserByEmail(String email) {
        Connection connection = DBConnection.getConnection();
        System.out.println(connection);
		try {
			//prepare query
			PreparedStatement pstmt = connection.prepareStatement("Select * from user where email = ? ");
			pstmt.setString(1, email);			
			//execute and get resultset
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {			
				System.out.println("Email: " + rs.getString("email") + "Password " + rs.getString("password"));
                                
                                return new User(rs.getString("name"), rs.getString("email"), rs.getString("password"),rs.getString("type"),rs.getBoolean("subscribed"));
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}         
        return null;
        
    }

    @Override
    public void createUser(User user) {
        	Connection connection = DBConnection.getConnection();
		try {
			//prepare query
			PreparedStatement pstmt = connection.prepareStatement("Insert into user(name, email,password, subscribed ) "
					+ "Values(?, ?, ?, ?)");
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPassword());
			pstmt.setBoolean(4, user.getSubscribed());			
			//execute
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
    }
    
}
