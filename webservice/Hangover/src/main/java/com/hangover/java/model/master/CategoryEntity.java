package com.hangover.java.model.master;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 11/30/15
 * Time: 11:14 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "category")
@XmlRootElement(name = "category")
public class CategoryEntity {
    
    private Long id;
    private String name;
    private String description;
    private CategoryEntity parentCategory;
    private int level=0;
    private List<CategoryEntity> childCategories;


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(unique = true, nullable = false, updatable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id", nullable = true)
    public CategoryEntity getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(CategoryEntity parentCategory) {
        this.parentCategory = parentCategory;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentCategory", fetch = FetchType.LAZY)
    public List<CategoryEntity> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(List<CategoryEntity> childCategories) {
        this.childCategories = childCategories;
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "category='" + name + '\'' +
                '}';
    }
}
