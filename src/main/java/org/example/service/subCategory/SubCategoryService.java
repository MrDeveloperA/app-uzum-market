package org.example.service.subCategory;

import org.example.entities.SubCategory;
import org.example.service.BaseService;

import java.sql.SQLException;

public interface SubCategoryService extends BaseService<SubCategory> {
    SubCategory get(String name) throws SQLException;
}
