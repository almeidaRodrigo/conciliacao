package conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoFactory {

	private static StringBuilder urlBuilder = new StringBuilder();
	private static String driver;
	private static String user;
	private static String pass;
	private static String host;
	private static int port;
	private static String serviceName;
	private static Connection con = null;
	private static String url;

	public static Connection connect(String driver, String user, String pass, String host, int port,
			String serviceName) throws Exception {

		ConexaoFactory.driver = driver;
		ConexaoFactory.user = user;
		ConexaoFactory.pass = pass;
		ConexaoFactory.host = host;
		ConexaoFactory.port = port;
		ConexaoFactory.serviceName = serviceName;

		ConexaoFactory.url = null;
		ConexaoFactory.urlBuilder.delete(0, ConexaoFactory.urlBuilder.length());

	
		ConexaoFactory.con = null;
		return open();
		
	}

	private static Connection open() throws Exception {
		String useSsl = "";

		try {

			switch (ConexaoFactory.driver) {
			case "oracle":
				Class.forName("oracle.jdbc.driver.OracleDriver");
				ConexaoFactory.driver = "jdbc:" + ConexaoFactory.driver + ":thin:@";
				break;

			case "mysql":
				useSsl = "?useSSL=false";
				Class.forName("com.mysql.jdbc.Driver");
				ConexaoFactory.driver = "jdbc:" + ConexaoFactory.driver + "://";
				break;
				
			case "sqlserver":
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				ConexaoFactory.driver = "jdbc:" + ConexaoFactory.driver + "://";
				break;
			}

			ConexaoFactory.urlBuilder.append(ConexaoFactory.driver + ConexaoFactory.host);

			if (ConexaoFactory.port > 0) {
				ConexaoFactory.urlBuilder.append(":" + ConexaoFactory.port);
			}

			if (!ConexaoFactory.serviceName.isEmpty()) {
				ConexaoFactory.urlBuilder.append(":" + ConexaoFactory.serviceName);
			}

			ConexaoFactory.url = ConexaoFactory.urlBuilder.toString() + useSsl;

			ConexaoFactory.con = DriverManager.getConnection(ConexaoFactory.url, ConexaoFactory.user,
					ConexaoFactory.pass);

		} catch (Exception e) {
			throw e;
		} finally {
			useSsl = null;
			ConexaoFactory.driver = null;
			ConexaoFactory.user = null;
			ConexaoFactory.pass = null;
			ConexaoFactory.host = null;
			ConexaoFactory.port = 0;
			ConexaoFactory.serviceName = null;
			ConexaoFactory.url = null;
		}

		return ConexaoFactory.con;

	}

}
