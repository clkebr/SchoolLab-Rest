package com.cydeo.controller;

import com.cydeo.dto.AddressDTO;
import com.cydeo.dto.ResponseWrapper;
import com.cydeo.dto.TeacherDTO;
import com.cydeo.service.AddressService;
import com.cydeo.service.ParentService;
import com.cydeo.service.StudentService;
import com.cydeo.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SchoolController {

    private final TeacherService teacherService;
    private final StudentService studentService;
    private final ParentService parentService;
    private final AddressService addressService;

    public SchoolController(TeacherService teacherService, StudentService studentService, ParentService parentService, AddressService addressService) {
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.parentService = parentService;
        this.addressService = addressService;
    }


    @GetMapping("/teachers")
    public List<TeacherDTO> fetchAllTeachers(){
        return teacherService.findAll();
    }

    @GetMapping("/students")
    public ResponseEntity<ResponseWrapper> fetchAllStudents(){
        return ResponseEntity.ok(new ResponseWrapper("Students are successfully retrieved", studentService.findAll()));
    }

    @GetMapping("/parents")
    public ResponseEntity<ResponseWrapper> fetchAllParents(){

        return ResponseEntity
                .status(202)
                .header("Parent","Returned")
                .body(new ResponseWrapper(true,"Parents are successfully retrieved", HttpStatus.OK.value(), parentService.findAll()));
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<ResponseWrapper> fetchAddressById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new ResponseWrapper("address "+id+" is successfully retrieved",addressService.findById(id)));

    }

    @PutMapping("/address/{id}")
    public ResponseEntity<ResponseWrapper> updateAddress(@PathVariable("id") Long id, @RequestBody AddressDTO addressDTO) throws Exception {
        addressDTO.setId(id);
        AddressDTO update = addressService.update(addressDTO);
        return ResponseEntity.ok(new ResponseWrapper("updated successfully",update));
    }


}
