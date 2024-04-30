package com.getposted.model;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

	T get(int t) throws SQLException;

	List<T> getAll() throws SQLException;

	int insert(T t) throws SQLException;

	int update(T t) throws SQLException;

	int delete(T t) throws SQLException;
}
