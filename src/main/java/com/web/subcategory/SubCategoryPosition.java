package com.web.subcategory;

import java.util.Arrays;
import java.util.HashMap;

public class SubCategoryPosition {
    
    String position;

    String idGroup;

    String nameGroup;

    Integer catId;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(String idGroup) {
        this.idGroup = idGroup;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }


 

    public SubCategoryPosition() {
       
    }


    public SubCategoryPosition(String position, Integer catId, String idGroup, String nameGroup) {
        this.position = position;
        this.idGroup = idGroup;
        this.nameGroup = nameGroup;
        this.catId = catId;
    }



    @Override
    public String toString() {
        return "SubCategoryPosition [position=" + position + ", idGroup=" + idGroup + ", nameGroup=" + nameGroup + "]";
    }

    public HashMap<Integer,String> getMap() {
        String[] idArr = (idGroup.split(","));
        String[] nameArr = (nameGroup.split(","));


        // System.out.println((Arrays.toString(idArr)));
        HashMap<Integer,String>  map = new HashMap<>();

        for (int i = 0; i < idArr.length; i++) {
            // Integer.parseInt(idArr[i]);
            map.put(Integer.parseInt(idArr[i]), nameArr[i]);
        }

        // System.out.println(map);
       return map;
        
    }

    public int getTotalSubOf() {
       return getMap().size();
    }

}
