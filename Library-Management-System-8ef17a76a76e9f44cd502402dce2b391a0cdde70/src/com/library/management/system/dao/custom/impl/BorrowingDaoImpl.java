/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.management.system.dao.custom.impl;

import com.library.management.system.dao.CrudUtil;
import com.library.management.system.dao.custom.BorrowingDao;
import com.library.management.system.entity.BorrowingEntity;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class BorrowingDaoImpl implements BorrowingDao {

    @Override
    public boolean create(BorrowingEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "INSERT INTO borrowings (id , user_id, book_id, borrowDate, returnDate,status) VALUES (?, ?, ?, ?, ?,?)",
                t.getId(), t.getUserId(), t.getBookId(), t.getBorrowDate(), t.getReturnDate(), t.getStatus());
    }

    @Override
    public boolean update(BorrowingEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE borrowings SET user_id = ?, book_id = ?, borrowDate = ?, returnDate = ?, status = ? WHERE id = ?",
                t.getUserId(), t.getBookId(), t.getBorrowDate(), t.getReturnDate(), t.getStatus(), t.getId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM borrowings WHERE id = ?", id);
    }

    @Override
    public BorrowingEntity get(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM borrowings WHERE id = ?", id);
        if (rst.next()) {
            BorrowingEntity entity = new BorrowingEntity(rst.getString("id"), rst.getString("user_id"),
                    rst.getString("book_id"), rst.getDate("borrowDate"), rst.getDate("returnDate"),
                    rst.getString("status"));
            return entity;
        }
        return null;
    }

    @Override
    public ArrayList<BorrowingEntity> getAll() throws Exception {
        ArrayList<BorrowingEntity> BorrowingEntities = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Books");
        while (rst.next()) {
            BorrowingEntity entity = new BorrowingEntity(rst.getString("id"), rst.getString("user_id"),
                    rst.getString("book_id"), rst.getDate("borrowDate"), rst.getDate("returnDate"),
                    rst.getString("status"));
            BorrowingEntities.add(entity);
        }
        return BorrowingEntities;
    }

}
