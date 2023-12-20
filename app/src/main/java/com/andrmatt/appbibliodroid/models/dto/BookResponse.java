package com.andrmatt.appbibliodroid.models.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookResponse {

    @SerializedName("data")
    private List<Datos> data;

    @SerializedName("meta")
    private Meta meta;

    public List<Datos> getData() {
        return data;
    }

    public void setData(List<Datos> data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public static class Datos{

        @SerializedName("id")
        private int id;

        @SerializedName("attributes")
        private Atributes attributes;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Atributes getAttributes() {
            return attributes;
        }

        public void setAttributes(Atributes attributes) {
            this.attributes = attributes;
        }

        public static class Atributes{

            @SerializedName("titulo")
            private String titulo;

            @SerializedName("sipnosis")
            private String sipnosis;

            @SerializedName("url_imagen")
            private String url_imagen;

            @SerializedName("cant_pag")
            private int cant_pag;

            @SerializedName("anio_publicacion")
            private String anio_publicacion;

            @SerializedName("autores")
            private List<Autor> autores;

            public String getTitulo() {
                return titulo;
            }

            public void setTitulo(String titulo) {
                this.titulo = titulo;
            }

            public String getSipnosis() {
                return sipnosis;
            }

            public void setSipnosis(String sipnosis) {
                this.sipnosis = sipnosis;
            }

            public String getUrl_imagen() {
                return url_imagen;
            }

            public void setUrl_imagen(String url_imagen) {
                this.url_imagen = url_imagen;
            }

            public int getCant_pag() {
                return cant_pag;
            }

            public void setCant_pag(int cant_pag) {
                this.cant_pag = cant_pag;
            }

            public String getAnio_publicacion() {
                return anio_publicacion;
            }

            public void setAnio_publicacion(String anio_publicacion) {
                this.anio_publicacion = anio_publicacion;
            }

            public List<Autor> getAutores() {
                return autores;
            }

            public void setAutores(List<Autor> autores) {
                this.autores = autores;
            }
        }

        class Autor{

            private String nombre;

            public String getNombre() {
                return nombre;
            }

            public void setNombre(String nombre) {
                this.nombre = nombre;
            }
        }
    }

    class Meta{
        pagination pagination;
        public class pagination{
            private int page;
            private int pageSize;
            private int pageCount;
            private int total;

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getPageCount() {
                return pageCount;
            }

            public void setPageCount(int pageCount) {
                this.pageCount = pageCount;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }
        }
    }
}


