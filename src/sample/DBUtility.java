/**
 *    Name:       Wyatt Kirschner
 *    Student ID: 200407722
 *    Date:       11/01/20
 *    Notes:    All notes will be placed in a README.md
 */
package sample;

import javafx.scene.chart.PieChart;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This DBUtility class is for creating the connection that is needed for the database to work
 * The username and password for this database is stated below!
 */


public class DBUtility {
    private static String username = "root";
    private static String password = "";

    /**
     * This will insert the product into the database and the objects table
     * @param repsychleObjectContainer
     * @return
     * @throws SQLException
     */
    public static int insertNewProduct(RepsychleObjectContainer repsychleObjectContainer) throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        int productID = -1;

        try {
            //  1. Create the connection for the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecoScore", username, password);
            //  2. Create the SQL String
            String sql = "INSERT INTO objects (brandName, productName, resinID, material, disposal, ecoComment, ecoScore) VALUES (?,?,?,?,?,?,?)";

            //  3. Prime the connection with the ProductID
            preparedStatement = conn.prepareStatement(sql, new String[] {
                    "id"
            });

            //  4. Binding for the INSERT statement
            preparedStatement.setString(1, repsychleObjectContainer.getBrandName());
            preparedStatement.setString(2, repsychleObjectContainer.getObjectName());
            preparedStatement.setInt(3, repsychleObjectContainer.getResinIdCode());
            preparedStatement.setString(4, repsychleObjectContainer.getMaterial());
            preparedStatement.setString(5, repsychleObjectContainer.getDisposal());
            preparedStatement.setString(6, repsychleObjectContainer.getEcoDoc());
            preparedStatement.setString(7, repsychleObjectContainer.getEcoScore());


            //  5. INSERT
            preparedStatement.executeUpdate();

            //  6. Retrieve Product ID
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()) {
                productID = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            {
                if (conn != null) {
                    conn.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                return productID;
            }
        }

    }




    /**
     * This will return the entries from the objects table from the database
     * @return
     * @throws SQLException
     */

    public static ArrayList < RepsychleObjectContainer > getAllProducts() throws SQLException {


        ArrayList < RepsychleObjectContainer > objects = new ArrayList < > ();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            //  1. Create the connection for the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecoScore", username, password);

            //  2. Create the statement object needed
            statement = conn.createStatement();

            //  3.  Retrieving the SQL statement
            resultSet = statement.executeQuery("SELECT * FROM objects");

            //  4.  Results will be obtained
            while (resultSet.next()) {

                RepsychleObjectContainer newObject = new RepsychleObjectContainer(
                        resultSet.getInt("id"),
                        resultSet.getString("brandName"),
                        resultSet.getString("productName"),
                        resultSet.getInt("resinID"),
                        resultSet.getString("material"),
                        resultSet.getString("disposal"),
                        resultSet.getString("ecoComment"),
                        resultSet.getString("ecoScore")
                );
                objects.add(newObject);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (resultSet != null) {
                resultSet.close();
            }
            return objects;

        }

    }






}