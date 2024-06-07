package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.documents.Empleado;
import com.example.demo.repository.EmpleadoRepository;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping
    public String showEmpleados(Model model) {
        List<Empleado> empleados = empleadoRepository.findAll();
        model.addAttribute("empleados", empleados);
        return "empleados";
    }

    @GetMapping("/showUpdateForm/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        Empleado empleado = empleadoRepository.findById((long) id).orElse(null);
        model.addAttribute("empleado", empleado);
        return "actualizar";
    }


    @PostMapping("/saveEmpleado")
    public String saveEmpleado(@RequestParam("id") Long id,
                               @RequestParam("nombre") String nombre,
                               @RequestParam("apellido") String apellido,
                               @RequestParam("clave") String clave,
                               @RequestParam("fechaEntrada") String fechaEntrada,
                               @RequestParam("fechaSalida") String fechaSalida) {
        Empleado empleado = new Empleado(id, nombre, apellido, clave, fechaEntrada, fechaSalida);
        empleadoRepository.save(empleado);
        return "redirect:/empleados";
    }

    @PostMapping("/updateEmpleado")
    public String updateEmpleado(@RequestParam("id") Long id,
                                 @RequestParam("nombre") String nombre,
                                 @RequestParam("apellido") String apellido,
                                 @RequestParam("clave") String clave,
                                 @RequestParam("fechaEntrada") String fechaEntrada,
                                 @RequestParam("fechaSalida") String fechaSalida) {
        Empleado empleado = empleadoRepository.findById(id).orElse(null);
        if (empleado != null) {
            empleado.setNombre(nombre);
            empleado.setApellido(apellido);
            empleado.setClave(clave);
            empleado.setFechaEntrada(fechaEntrada);
            empleado.setFechaSalida(fechaSalida);
            empleadoRepository.save(empleado);
        }
        return "redirect:/empleados";
    }

    @PostMapping("/deleteEmpleado")
    public String deleteEmpleado(@RequestParam("id") int id) {
        empleadoRepository.deleteById((long) id);
        return "redirect:/empleados";
    }
}

