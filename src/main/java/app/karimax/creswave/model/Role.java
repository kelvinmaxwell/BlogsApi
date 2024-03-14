package app.karimax.creswave.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "tbl_roles", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String name;



    public Role() {

    }

    public Role(String name) {
        this.name = name;
    }

    public Role(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return this.name;
    }

}
