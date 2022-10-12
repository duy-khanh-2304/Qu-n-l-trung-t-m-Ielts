package com.example.be.controller;

import com.example.be.entity.Class;
import com.example.be.entity.mapped.PaymentVoucher;
import com.example.be.request.ClassRequest;
import com.example.be.request.PaymentVoucherRequest;
import com.example.be.service.BaseService;
import com.example.be.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/class")
public class ClassController extends BaseController<Class> {
    public ClassController(BaseService<Class> baseService) {
        super(baseService);
    }

    @Autowired
    private ClassService classService;
    //post request
    @PreAuthorize("hasAnyAuthority('CLASS_POST')")
    @PostMapping("/post")
    public Class postRequest(@RequestBody @Valid ClassRequest classRequest, BindingResult bindingResult) {
        return classService.createRequest(classRequest, bindingResult);
    }

    //put request
    @PreAuthorize("hasAnyAuthority('CLASS_PUT')")
    @PutMapping("/put/{id}")
    public Class putRequest(@PathVariable(value = "id") long id, @RequestBody @Valid ClassRequest classRequest, BindingResult bindingResult) {
        return classService.updateRequest(id, classRequest, bindingResult);
    }
    // get class by department id
    @PreAuthorize("hasAnyAuthority('CLASS_READ')")
    @GetMapping("/department/{id}")
    List<Class> findClassByDepartmentId(@PathVariable(value = "id") long id){
        return classService.findClassByDepartmentId(id);
    }

    // get class by course id
    @PreAuthorize("hasAnyAuthority('CLASS_READ')")
    @GetMapping("/course/{id}")
    List<Class> findClassByCourseId(@PathVariable(value = "id") long id){
        return classService.findClassByCourseId(id);
    }

}
