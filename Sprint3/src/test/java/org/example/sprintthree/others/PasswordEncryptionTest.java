package org.example.sprintthree.others;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordEncryptionTest {

    @Test
    @DisplayName("Prueba cifrado de contraseña")
    public void testEncryptPassword() {
        PasswordEncryption passwordEncryption = new PasswordEncryption();
        String password = "password123";
        String encryptedPassword = passwordEncryption.encryptPassword(password);
        assertNotNull(encryptedPassword);
        assertNotEquals(password, encryptedPassword);
    }

    @Test
    @DisplayName("Prueba cifrado de contraseña con contraseña vacía")
    public void testEncryptEmptyPassword() {
        PasswordEncryption passwordEncryption = new PasswordEncryption();
        String password = "";
        String encryptedPassword = passwordEncryption.encryptPassword(password);
        assertNotNull(encryptedPassword);
        assertNotEquals(password, encryptedPassword);
    }

    @Test
    @DisplayName("Prueba cifrado de contraseña con contraseña nula")
    public void testEncryptNullPassword() {
        PasswordEncryption passwordEncryption = new PasswordEncryption();
        String password = null;
        String encryptedPassword = passwordEncryption.encryptPassword(password);
        assertNull(encryptedPassword);
    }
}