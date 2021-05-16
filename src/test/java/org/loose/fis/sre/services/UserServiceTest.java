package org.loose.fis.sre.services;

import org.apache.commons.io.FileDeleteStrategy;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.loose.fis.sre.exceptions.NoPasswordException;
import org.loose.fis.sre.exceptions.NoRoleSelectedException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.model.User;

import java.io.IOException;
import java.nio.*;

import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.assertions.api.Assertions.assertThat;

class UserServiceTest {

    public static final String OWNER = "Owner";

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before Class");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After Class");
    }

    @BeforeAll
    static void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-rezervare-cabana";
        //System.gc();
        //Thread.sleep(2000);
        //FileDeleteStrategy.FORCE.delete(FileSystemService.getApplicationHomeFolder().toFile());
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        //Files.delete(FileSystemService.getApplicationHomeFolder());
        FileSystemService.initDirectory();
        UserService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        System.out.println("After each");
    }


    @Test
    @DisplayName("Database is initialized, and there are no users")
    void testDatabaseIsInitializedAndNoUserIsPersisted() {
        //UserService.initDatabase();
        assertThat(UserService.getAllUsers()).isNotNull();
        assertThat(UserService.getAllUsers()).isEmpty();
    }

    @Test
    @DisplayName("User is successfully persisted to Database")
    void testUserIsAddedToDatabase() throws UsernameAlreadyExistsException, NoPasswordException, NoRoleSelectedException {
        //UserService.initDatabase();
        UserService.addUser(OWNER, OWNER, OWNER);
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        User user = UserService.getAllUsers().get(0);
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo(OWNER);
        assertThat(user.getPassword()).isEqualTo(UserService.encodePassword(OWNER, OWNER));
        assertThat(user.getRole()).isEqualTo(OWNER);
    }

    @Test
    @DisplayName("User can not be added twice")
    void testUserCanNotBeAddedTwice() {
        //UserService.initDatabase();
        assertThrows(UsernameAlreadyExistsException.class, () -> {
            UserService.addUser(OWNER, OWNER, OWNER);
            UserService.addUser(OWNER, OWNER, OWNER);
        });
    }

}
