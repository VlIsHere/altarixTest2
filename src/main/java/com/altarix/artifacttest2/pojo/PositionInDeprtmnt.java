package com.altarix.artifacttest2.pojo;


public class PositionInDeprtmnt implements Domain{
    private long idPosition;
    private String namePosition;

    @Override
    public long getId() {
        return idPosition;
    }

    public void setIdPosition(long idPosition) {
        this.idPosition = idPosition;
    }

    public String getNamePosition() {
        return namePosition;
    }

    public void setNamePosition(String namePosition) {
        this.namePosition = namePosition;
    }
}
