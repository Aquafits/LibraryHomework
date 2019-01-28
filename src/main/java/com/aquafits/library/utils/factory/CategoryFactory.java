package com.aquafits.library.utils.factory;

import com.aquafits.library.data.Mock;
import com.aquafits.library.data.model.books.Category;

import java.util.List;

public class CategoryFactory {
    private static CategoryFactory ourInstance = new CategoryFactory();

    public static CategoryFactory getInstance() {
        return ourInstance;
    }

    private List<Category> categories = Mock.getInstance().categories;

    private CategoryFactory() {
    }

    public Category getCategory(String categoryName){
        for(Category c: categories){
            if(c.getName().equals(categoryName)){
                return c;
            }
        }
        return new Category(""+(categories.size()+1), categoryName);
    }
}
