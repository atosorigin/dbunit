package dbunit.tests;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;


public class SampleTest extends DBTestCase{

	 public SampleTest(String name)
	    {
	        super( name );
	        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.jdbc.Driver" );
	        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://localhost:3306/kickstart" );
	        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root" );
	        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "root" );
	    }

	 
	 @Before
	 protected void setUp() throws Exception
	    {
	        super.setUp();
	        Connection jdbcConnection = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/kickstart", "root", "root");
	        
	        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

	        IDataSet dataSet = getDataSet();

	        try
	        {
	            DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
	        }
	        finally
	        {
	            connection.close();
	        }
	    }

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dataset.xml"));
	}
	
	@Test
	public void testa() {
		
	}

}
