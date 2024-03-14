package app.karimax.creswave.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.*;


/**
 * users table and the attributes
 **/
@Data
@Entity

@Table(name = "tbl_users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email", nullable = false, unique = true) // Make email field unique
    private String email;

    @Column(name = "username", nullable = false, unique = true) // Make username field unique
    private String username;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at")
    private Timestamp updated_at;
    @Basic
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "last_login")
    private Timestamp last_login;
    @Basic
    @Column(name = "status", nullable = false)
    private Integer status;


    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(
            name = "tbl_users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }


    // Constructor
    public User() {
        this.roles = new HashSet<>(); // Initialize roles to avoid NullPointerException
    }

}