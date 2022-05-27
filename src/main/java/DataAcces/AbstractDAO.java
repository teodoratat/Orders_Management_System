package DataAcces;

import Model.Client;
import connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Generic class that manages the data from the Database.
 * It also generates queries and implements the CRUD methods for the tables.
 * @param <T> can be replaced with either Client, Product or Order.
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;
    private final static String findStatementString = "SELECT * FROM student where id  = ?";

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM `");
        sb.append(type.getSimpleName());
        sb.append("` WHERE " + field + " =?");
        return sb.toString();
    }
    /**
     * Method that creates the select all query for the tables in the warehouse database.
     * @return the query as a string.
     */
    private String createSelectAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM `");
        sb.append(type.getSimpleName());
        sb.append("`");
        return sb.toString();
    }
    /**
     * the method that generates the query used for inserting data in the tables
     * @param fields the attributes of the instance that must be inserted
     * @return the insert query
     */
    public String createInsertQuery(ArrayList<String> fields) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO `");
        sb.append(type.getSimpleName()+"`(");
        for(int i = 0; i< fields.size(); ++i) {
            sb.append("`"+ fields.get(i) +"`");
            if(i != fields.size()-1){
                sb.append(",");
            }
        }
        sb.append(") VALUES (");
        for(int i=0; i<fields.size(); ++i) {
            sb.append("?");
            if(i != fields.size()-1){
                sb.append(",");
            }
        }
        sb.append(")");
        return sb.toString();
    }



    /**
     * the method that generates the query used for editing data in the tables
     * @return the update query
     */
    private String createEditQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE `");
        sb.append(type.getSimpleName());
        sb.append("` SET ");
        for(Field field : type.getDeclaredFields()){
            sb.append(field.getName()).append("=?, ");
        }
        sb.delete(sb.length()-2,sb.length()-1);
        sb.append(" WHERE ").append("ID").append("=?");
        return sb.toString();
    }



    public T findById(String id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("ID");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, id);
            resultSet = statement.executeQuery();

            if(resultSet.isBeforeFirst()==false)
                return null;
            else
            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Method that uses the select all query to extract all the data from a table.
     * @return the data if it is successfully extracted, null otherwise.
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAll();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            if(resultSet.isBeforeFirst()==false)
                return null;
            else
                return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Method that uses the createInsertQuery method to insert the new instance in the table.
     * @return true if inserted successfully, false otherwise
     */
    public boolean insert(T Object){
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<String> fieldNames = new ArrayList<>();

        for(Field field:type.getDeclaredFields()){
            fieldNames.add(field.getName());
        }
        String query = createInsertQuery(fieldNames);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int position = 1;
            for(Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                statement.setObject(position++,field.get(Object));
            }
            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + " DAO:insert "+ e.getMessage());
            return false;
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return true;
    }/**
     * Method that uses the createUpdateQuery method to update a row in the table.
     * @param object parameters of the updated instance
     * @return true if updated successfully, false otherwise
     */
    public boolean update(T object){
        Connection connection = null;
        PreparedStatement statement = null;

        String query = createEditQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int position = 1;
            for(Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                statement.setObject(position++,field.get(object));
            }
            Field field = type.getDeclaredFields()[0];
            field.setAccessible(true);
            statement.setObject(position++,field.get(object));
            statement.executeUpdate();
            return true;
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + " DAO:insert "+ e.getMessage());
            return false;
        }
        finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Method that deletes a row in the table.
     * @param id id of the row that must be deleted
     * @return true if deleted successfully, false otherwise
     */
    public void delete(int id) {
        String query = "DELETE FROM " + type.getSimpleName() + " WHERE id = ?";
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Method that is using reflection to generate a list of objects of type T
     * corresponding to the table.
     * @param resultSet resulted by executing the query
     * @return the generated list of objects
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }
}
