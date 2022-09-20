package manager;

import db.DBConnectionProvider;
import model.Category;
import model.Item;
import model.User;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
public class ItemManager {

    private UserManager userManager = new UserManager();
    private CategoryManager categoryManager = new CategoryManager();
    private final Connection CONNECTION = DBConnectionProvider.getInstance().getConnection();

    public void AddItem(Item item){
        String sql = "insert into item (title, price, category_id, pic_url, user_id) values(?,?,?,?,?)";
        try {
            PreparedStatement ps = CONNECTION.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,item.getTitle());
            ps.setDouble(2,item.getPrice());
            ps.setInt(3,item.getCategory().getId());
            ps.setString(4,item.getPicUrl());
            ps.setInt(5,item.getUser().getId());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if(resultSet.next()){
                int itemId = resultSet.getInt(1);
                item.setId(itemId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Item> getAll() {
        String sql = "select * from item";
        List<Item> items = new ArrayList<>();
        try {
            PreparedStatement ps = CONNECTION.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                items.add(getItemFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public Item getItemById(int id){
        String sql = "Select * from item where id = " + id;
        try {
            PreparedStatement ps = CONNECTION.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery(sql);
            if (resultSet.next()) {
                return getItemFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private Item getItemFromResultSet(ResultSet resultSet) {
        Item item = new Item();
        try {
            item.setId(resultSet.getInt("id"));
            item.setTitle(resultSet.getString("title"));
            item.setPrice(resultSet.getDouble("price"));
            int categoryId = resultSet.getInt("category_id");
            Category category = categoryManager.getById(categoryId);
            item.setPicUrl(resultSet.getString("pic_url"));
            int userId = resultSet.getInt("user_id");
            User user = userManager.getUserById(userId);

            item.setCategory(category);
            item.setUser(user);
            return item;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
