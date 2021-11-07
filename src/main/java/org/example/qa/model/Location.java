package org.example.qa.model;


public enum Location {
    OSTRAVA(10202002L, "Ostrava"),
    BRNO(10202000L, "Brno");

    public final Long id;
    public final String name;

    Location(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

