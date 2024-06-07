package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.documents.Empleado;
import com.example.demo.repository.EmpleadoRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	EmpleadoRepository empleadoRepository;
	
	@GetMapping("/login")
    public String login() {
        return "login";
    }

	@PostMapping("/login")
    public String login(@RequestParam("id") Long id, @RequestParam("clave") String clave, HttpSession session) {
        Empleado empleado = empleadoRepository.findById(id).orElse(null);
        if (empleado != null && empleado.getClave().equals(clave)) {
            session.setAttribute("empleado", empleado);
            if (id == 123L) {
                return "redirect:/index";
            } else {
                session.invalidate();
                return "redirect:/login";
            }
        } else {
            return "login";
        }
    }


    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    
}
