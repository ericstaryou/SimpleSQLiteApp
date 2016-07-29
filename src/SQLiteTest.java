import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/***************************************************************************************
*    Originator: John McNeil
*    Year of Distribution: 2016
*    Title of Video: Java with SQLite embedded database
*    Date of Video: 21 January 2016
*    Video Website Address: https://www.youtube.com/watch?v=JPsWaI5Z3gs
*    Accessed Date: 29 July 2016
*
***************************************************************************************/

public class SQLiteTest {

	private static Connection con;
	private static boolean hasData = false;
	
	public ResultSet displayUsers() throws ClassNotFoundException, SQLException{
		if(con == null) {
			getConnection();
		}
		
		Statement state = con.createStatement();
		ResultSet res = state.executeQuery("SELECT fname, lname FROM user");
		return res;
	}

	private void getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:SQLiteTest1.db");
		initialise();
	}

	private void initialise() throws SQLException {
		if(!hasData){
			hasData = true;
			
			Statement state = con.createStatement();
			ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='user'");
			if (!res.next()){
				System.out.println("Building the User table with prepopulated values.");
				// need to build the table
				Statement state2 = con.createStatement();
				state2.execute("CREATE TABLE user(id integer," 
						+ "fname varchar(60)," + "lname varchar(60)," 
						+ "primary key(id));");
				
				//inserting some sample data
				PreparedStatement prep = con.prepareStatement("INSERT INTO user values(?,?,?);");
				prep.setString(2, "Eric");
				prep.setString(3, "Yew");
				prep.execute();
				
				PreparedStatement prep2 = con.prepareStatement("INSERT INTO user values(?,?,?);");
				prep2.setString(2, "Pingkee");
				prep2.setString(3, "Chan");
				prep2.execute();
			}
		}
	}
	
	public void addUser(String firstName, String lastName) throws ClassNotFoundException, SQLException{
		if(con == null){
			getConnection();
		}
		
		PreparedStatement prep = con.prepareStatement("INSERT INTO user values(?,?,?);");
		prep.setString(2, firstName);
		prep.setString(3, lastName);
		prep.execute();
	}
}
