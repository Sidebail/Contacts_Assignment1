package Models;

import java.sql.*;
import java.util.ArrayList;

public class DBConnect {

    DriverManager conn;

    private static String login = "root";
    private static String password = "";




    public static ArrayList<Profile> getContacts() throws SQLException {

        ArrayList<Profile> profiles = new ArrayList<Profile>();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            //1-Connect to DB
            /*
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/contacts?useSSL=false",
                    login,password);
            */
            /**
             * Connecting to AZURE DB
             */
            conn = DriverManager.getConnection("jdbc:sqlserver://mvcmusicserver.database.windows.net:1433;database=contacts;user=ascotbailey2@mvcmusicserver;password=voVa210699;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");


            //2 Create a Statement Object
            statement = conn.createStatement();

            //3 Create the sql query
            resultSet = statement.executeQuery("SELECT * FROM contacts");

            //4 Loop over the results and add to ArrayList

            while (resultSet.next()){
                //  public Profile(String firstName,String lastName,String eMail,String bio,String address,String phone,String sex,String birthday ){

                Profile newProfile = new Profile(
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("email"),
                        resultSet.getString("bio"),
                        resultSet.getString("address"),
                        resultSet.getString("phone"),
                        resultSet.getString("sex"),
                        resultSet.getString("birthday")
                        );
                    profiles.add(newProfile);
            }


        } catch (SQLException e) {
            System.err.println(e);
        }

        finally {
            if (conn != null){
                conn.close();
            }
            if (statement != null){
                statement.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
        }

        return profiles;
    }

    public static ArrayList<Profile> getSearchedContacts(String search) throws SQLException {

        ArrayList<Profile> profiles = new ArrayList<Profile>();
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        search = '%'+search+'%';
        try{
            //1-Connect to DB
            conn = DriverManager.getConnection("jdbc:sqlserver://mvcmusicserver.database.windows.net:1433;database=contacts;user=ascotbailey2@mvcmusicserver;password=voVa210699;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");

            //2 Create a Statement Object

            String sql = "SELECT * FROM contacts WHERE lastName LIKE ? OR firstName LIKE ?;";



            statement = conn.prepareStatement(sql);
            statement.setString(1,search);
            statement.setString(2,search);

            System.out.println(statement.toString());

            resultSet = statement.executeQuery();

            //3 Create the sql query11
            //resultSet = statement.executeQuery("SELECT * FROM contacts WHERE lastName LIKE '%?%' OR firstName LIKE '%?%'");

            //4 Loop over the results and add to ArrayList

            while (resultSet.next()){
                //public Profile(String firstName,String lastName,String eMail,String bio,String address,String phone,String sex,String birthday ){

                Profile newProfile = new Profile(
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("email"),
                        resultSet.getString("bio"),
                        resultSet.getString("address"),
                        resultSet.getString("phone"),
                        resultSet.getString("sex"),
                        resultSet.getString("birthday")
                );
                profiles.add(newProfile);
            }


        } catch (SQLException e) {
            System.err.println(e);
        }

        finally {
            if (conn != null){
                conn.close();
            }
            if (statement != null){
                statement.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
        }

        return profiles;
    }



    /**
     * This method recieves a Profile object and will sort it in a DB
     * @param newProfile - must be a valid Profile object
     * @throws SQLException
     */
    public static void insertProfileIntoDB(Profile newProfile) throws SQLException {
        System.out.println("Zahodim");
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            //1-Connect to DB
            conn = DriverManager.getConnection("jdbc:sqlserver://mvcmusicserver.database.windows.net:1433;database=contacts;user=ascotbailey2@mvcmusicserver;password=voVa210699;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");

            String sql = "INSERT INTO contacts (firstName, lastName, sex, birthday, email, bio, address,phone) VALUES (?,?,?,?,?,?,?,?);";

            ps = conn.prepareStatement(sql);

            ps.setString(1,newProfile.getFirstName());
            ps.setString(2,newProfile.getLastName());
            ps.setString(3,newProfile.getSex());
            ps.setString(4,newProfile.getBirthday());
            ps.setString(5,newProfile.getEMail());
            ps.setString(6,newProfile.getBio());
            ps.setString(7,newProfile.getAddress());
            ps.setString(8,newProfile.getPhone());

            System.out.println(ps.toString());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e);
        }
        finally {
            if (conn != null)
                conn.close();
            if(ps != null)
                ps.close();
        }
    }

}
