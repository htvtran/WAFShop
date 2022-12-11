package com.web.data.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.web.data.SubCatergoryRepository;
import com.web.subcategory.SubCategory;


@Service
public class SubCategoryServiceImpl implements SubCategoryService{

    @Autowired
    @Qualifier("subcatrepo")
    SubCatergoryRepository subDao;
    


    @Override
    public HashMap<String, List<SubCategory>> groupAllSubCatByPostion() {
        return null;
    }

    
    
}
