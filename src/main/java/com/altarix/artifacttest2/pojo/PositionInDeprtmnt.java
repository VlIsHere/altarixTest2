package com.altarix.artifacttest2.pojo;


public class PositionInDeprtmnt implements Domain{
    private long id;
    private String namePosition;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNamePosition() {
        return namePosition;
    }

    public void setNamePosition(String namePosition) {
        this.namePosition = namePosition;
    }
}
