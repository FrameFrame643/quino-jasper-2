package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "people") // ระบุชื่อตารางในฐานข้อมูล
public class Person extends PanacheEntity {
    public String name; // แมปกับคอลัมน์ name ในตาราง

    // Constructor เปล่าสำหรับ JPA
    public Person() {}

    // Constructor ที่กำหนดค่า
    public Person(String name) {
        this.name = name;
    }
}
