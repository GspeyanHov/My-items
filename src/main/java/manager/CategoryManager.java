package manager;

import db.DBConnectionProvider;
import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryManager {

    private final static Connection CONNECTION = DBConnectionProvider.getInstance().getConnection();

    public Category getById(int id){
        String sql = "select * from category where id = " + id;
        try {
            PreparedStatement ps = CONNECTION.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                return getCategoryFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private Category getCategoryFromResultSet(ResultSet resultSet){
        Category category = new Category();
        try {
            category.setId(resultSet.getInt("id"));
            category.setName(resultSet.getString("name"));
            return category;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
