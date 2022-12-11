package com.web.util;

import java.util.List;

import org.springframework.beans.support.PagedListHolder;

public class PageHolder {
    

    public static PagedListHolder getPageOf(List<?> list, int size, int pagenum) {
        PagedListHolder page = new PagedListHolder(list);
		page.setPageSize(8); // number of items per page
		page.setPage(0);

        return page;
    }

}
