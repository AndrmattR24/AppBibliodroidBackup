package com.andrmatt.appbibliodroid.models.dto;

import java.util.List;

public class BookResponse {
    public MetaResponse meta;

    public List<BookItem> data;

    public class BookItem {
        public int id;
        public Attribute attributes;

        public class Attribute {
            public String titulo;
            public String sipnosis;

            public String url_imagen;
        }
    }
}


