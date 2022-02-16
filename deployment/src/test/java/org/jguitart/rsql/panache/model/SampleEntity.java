package org.jguitart.rsql.panache.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class SampleEntity extends PanacheEntity {

    String name;
    int size;
    double capacity;
    long timemillis;
    EnumValue enumTest;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public long getTimemillis() {
        return timemillis;
    }

    public void setTimemillis(long timemillis) {
        this.timemillis = timemillis;
    }

    public EnumValue getEnumTest() {
        return enumTest;
    }

    public void setEnumTest(EnumValue enumTest) {
        this.enumTest = enumTest;
    }

    public enum EnumValue {
        value1, value2, value3
    }
}
