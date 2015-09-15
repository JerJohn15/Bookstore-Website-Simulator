import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Sets up the configurations needed
 * to access the database. 
 * @author Jeremiah
 *
 */
public class DataSource {

	
	
    private  DataSource     datasource;
    private ComboPooledDataSource cpds;

    //Load all the neccessary information to establish DB connection 
    public DataSource() throws IOException, SQLException, PropertyVetoException {
        cpds = new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.jdbc.Driver"); 
        cpds.setJdbcUrl("jdbc:mysql://localhost/test");
        cpds.setUser("root");
        cpds.setPassword("ProgJ16");

   

    }

    //retrieves the datasource
    public  DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        if (datasource == null) {
            datasource = new DataSource();
            return datasource;
        } else {
            return datasource;
        }
    }

    //gets a current connection
    public Connection getConnection() throws SQLException {
        return this.cpds.getConnection();
    }
	
	
	
}
