package com.donks.bci.bcichallenge.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    private final Pattern emailRegex = Pattern.compile("\\w*@\\w*\\.\\w*");


    @Test
    void testRegex(){
        String s = "hola@mundo.com";
        String s2 = "hola@mund.cl";

        assertTrue(emailRegex.matcher(s).matches());
        assertTrue(emailRegex.matcher(s2).matches());
    }

}