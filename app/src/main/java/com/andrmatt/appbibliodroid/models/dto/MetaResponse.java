package com.andrmatt.appbibliodroid.models.dto;

public class MetaResponse {

    public Pagination pagination;

    public class Pagination {
        private int page;
        private int pageSize;
        private int total;

        public int getPage() {
            return page;
        }

        public int getPageSize() {
            return pageSize;
        }

        public int getTotal() {
            return total;
        }
    }

}
