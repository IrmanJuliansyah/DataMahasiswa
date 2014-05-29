package database.mahasiwa;


import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
public class KONEKSI{
	
private static Connection koneksi;
public static Connection getKONEKSI(){
	if(koneksi == null){
		try
		{
String url = "jdbc:mysql://localhost:3306/universitas";
String username = "root";
String password = "";
DriverManager.registerDriver(new com.mysql.jdbc.Driver());
koneksi =
DriverManager.getConnection(url,username,password);

} catch(Exception ex)
{
System.out.println(ex);
}
}return koneksi;
}
public static PreparedStatement prepareStatement(String query) {
	// TODO Auto-generated method stub
	return null;
}
}

