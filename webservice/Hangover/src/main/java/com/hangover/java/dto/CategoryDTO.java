package com.hangover.java.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 8/23/16
 * Time: 11:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class CategoryDTO extends BaseDTO{

    private String name;
    private String description;
    private CategoryDTO parentCategory;
    private int level=0;
    private List<CategoryDTO> childCategories;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryDTO getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(CategoryDTO parentCategory) {
        this.parentCategory = parentCategory;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<CategoryDTO> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(List<CategoryDTO> childCategories) {
        this.childCategories = childCategories;
    }

    public void addChildCategory(CategoryDTO categoryDTO){
        if(null == getChildCategories())
            setChildCategories(new ArrayList<CategoryDTO>());
        getChildCategories().add(categoryDTO);
    }
}
