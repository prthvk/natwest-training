package com.stackroute.keepnote.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/*
 * Please note that this class is annotated with @Document annotation
 * @Document identifies a domain object to be persisted to MongoDB.
 *  */
@Document
public class Category {

    /*
     * This class should have five fields
     * (categoryId,categoryName,categoryDescription,
     * categoryCreatedBy,categoryCreationDate). Out of these five fields, the field
     * categoryId should be annotated with @Id. This class should also contain the
     * getters and setters for the fields along with the no-arg , parameterized
     * constructor and toString method. The value of categoryCreationDate should not
     * be accepted from the user but should be always initialized with the system
     * date.
     */

    @Id
    private String categoryId;
    private String categoryName;
    private String categoryDescription;
    private String categoryCreatedBy;
    private Date categoryCreationDate;


    public Category() {
    }

    public Category(String categoryId, String categoryName, String categoryDescription, String categoryCreatedBy) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.categoryCreatedBy = categoryCreatedBy;
        this.categoryCreationDate = new Date();
    }

    public String getId() {
        return this.categoryId;
    }

    public void setId(String id) {
        this.categoryId = id;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return this.categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryCreatedBy() {
        return this.categoryCreatedBy;
    }

    public void setCategoryCreatedBy(String categoryCreatedBy) {
        this.categoryCreatedBy = categoryCreatedBy;
    }

    public Date getCategoryCreationDate() {
        return this.categoryCreationDate;
    }

    public void setCategoryCreationDate(Date categoryCreationDate) {
        this.categoryCreationDate = categoryCreationDate;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId='" + categoryId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", categoryDescription='" + categoryDescription + '\'' +
                ", categoryCreatedBy='" + categoryCreatedBy + '\'' +
                ", categoryCreationDate=" + categoryCreationDate +
                '}';
    }
}
