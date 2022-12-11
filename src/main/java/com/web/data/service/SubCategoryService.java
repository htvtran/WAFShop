package com.web.data.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.web.subcategory.SubCategory;



public interface SubCategoryService {

    public HashMap<String, List<SubCategory>> groupAllSubCatByPostion();



}
