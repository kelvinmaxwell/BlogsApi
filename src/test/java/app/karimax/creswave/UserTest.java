package app.karimax.creswave;

import app.karimax.creswave.model.Role;
import app.karimax.creswave.model.User;
import app.karimax.creswave.repository.RoleRepository;
import app.karimax.creswave.repository.UserRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)

public class UserTest {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testConstructor() {
        User user = new User();
        assertNotNull(user);
    }

    @Test
    void testGetterAndSetter() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setUsername("testuser");
        user.setPassword("password");
        user.setCreated_at(Timestamp.valueOf("2024-03-14 12:00:00"));
        user.setUpdated_at(Timestamp.valueOf("2024-03-14 12:00:00"));
        user.setLast_login(Timestamp.valueOf("2024-03-14 12:00:00"));
        user.setStatus(1);

        assertEquals(1L, user.getId());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("testuser", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals(Timestamp.valueOf("2024-03-14 12:00:00"), user.getCreated_at());
        assertEquals(Timestamp.valueOf("2024-03-14 12:00:00"), user.getUpdated_at());
        assertEquals(Timestamp.valueOf("2024-03-14 12:00:00"), user.getLast_login());
        assertEquals(1, user.getStatus());
    }

    @Test
    void testAddRole() {
        User user = new User();
        Role role = new Role();
        user.addRole(role);
        assertEquals(1, user.getRoles().size());
        assertTrue(user.getRoles().contains(role));
    }

    @Test
    void testSetRoles() {
        User user = new User();
        Role role1 = new Role("ADMIN");
        Role role2 = new Role("USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role1);
        roles.add(role2);
        user.setRoles(roles);
        assertEquals(2, user.getRoles().size());
        assertTrue(user.getRoles().contains(role1));
        assertTrue(user.getRoles().contains(role2));
    }

    @Test
    @Order(2)
    public void testCreateUser() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setUsername("testuser");
        user.setPassword("password");
        user.setStatus(1);
        user.setCreated_at(Timestamp.valueOf("2024-03-14 12:00:00"));
        user.setLast_login(Timestamp.valueOf("2024-03-14 12:00:00"));

        // When
        User existing_user = userRepository.findByEmail("test@example.com");
        if (existing_user != null) {
            user.setId(existing_user.getId());//set id of new updates
            existing_user= userRepository.save(user); //update existing user
        }else {

            user.addRole(roleRepository.findByName("ADMIN"));
            existing_user= userRepository.save(user); //create new user
        }



        // Verify that the USER is saved
        assertNotNull(existing_user.getId()); // Ensure the ID is generated after saving

        // Retrieve the saved ADMIN
        User retrievedUser = userRepository.findByEmail("test@example.com");
        assertNotNull(retrievedUser); // Check if the user was successfully saved
        assertEquals("test@example.com", retrievedUser.getEmail());
        assertEquals("testuser", retrievedUser.getUsername());
        assertEquals("password", retrievedUser.getPassword());
    }


    @Test
    @Order(1)
    public void testCreateRoles() {
        Role admin = new Role("ADMIN");

        Role existing_admin = roleRepository.findByName("ADMIN");
        if (existing_admin != null) {
            admin.setId(existing_admin.getId());
            existing_admin = roleRepository.save(admin); // update existing ADMIN
        } else {

            existing_admin = roleRepository.save(admin); //create new ADMIN
        }


        // Verify that the ADMIN is saved
        assertNotNull(existing_admin.getId()); // Ensure the ID is generated after saving

        // Retrieve the saved ADMIN
        Role retrieved_admin = roleRepository.findByName("ADMIN");
        assertNotNull(retrieved_admin); // Check if the ADMIN was successfully saved
        assertEquals("ADMIN", retrieved_admin.getName());

    }




}
