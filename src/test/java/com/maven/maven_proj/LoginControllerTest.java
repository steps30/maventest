package com.maven.maven_proj;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.maven.maven_proj.domain.Login;
import com.maven.maven_proj.service.LoginService;


@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {


   @Autowired
   private MockMvc mockMvc;


   @MockBean
   private LoginService loginService;


   @Test
   public void testSuccessfulLogin() throws Exception {
       Login user = new Login();
       user.setUsername("admin");
       when(loginService.log(anyString(), anyString())).thenReturn(user);
       mockMvc.perform(post("/login")
               .param("username", "admin")
               .param("password", "password"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/welcome"));
   }


   @Test
   public void testFailedLogin() throws Exception {
       when(loginService.log(anyString(), anyString())).thenReturn(null);
       mockMvc.perform(post("/login")
               .param("username", "invalidUser")
               .param("password", "wrongPass"))
               .andExpect(status().isOk())
               .andExpect(view().name("login"))
               .andExpect(model().attributeExists("error"));
   }
}


