package com.ems.controller;

import com.ems.entity.Employee;
import com.ems.model.EmployeeModel;
import com.ems.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize; // 1. IMPORT ADDED
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeRepository repository;

	@GetMapping("/index")
	public String getIndexPage() {
		return "index";
	}

	@PreAuthorize("hasRole('MANAGER')")
	@GetMapping("/addEmployee")
	public String getAddEmployeePage(Model model) {
		EmployeeModel empModel = new EmployeeModel();
		model.addAttribute("empModel", empModel);
		return "addEmployee";
	}

	@PreAuthorize("hasRole('MANAGER')")
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("empModel") EmployeeModel empModel, Model model) {
		Employee e = new Employee();
		BeanUtils.copyProperties(empModel, e);
		boolean flag = repository.existsById(e.getEmpno());
		if (flag) {
			model.addAttribute("message", "Employee with given empno already exist");
		} else {
			repository.save(e);
			model.addAttribute("message", "Employee is added to Database");
		}
		return "index";
	}

	@GetMapping("/listEmployees")
	public String listEmployees(Model model) {
		List<Employee> empList = repository.findAll();

		List<EmployeeModel> empModelList = new ArrayList<>();

		empList.forEach(e -> {
			EmployeeModel emodel = new EmployeeModel();
			BeanUtils.copyProperties(e, emodel);
			empModelList.add(emodel);
		});
		model.addAttribute("empModelList", empModelList);
		return "employeesList";
	}

	@PreAuthorize("hasRole('MANAGER')")
	@GetMapping("/editEmployee")
	public String editEmployeePage(@RequestParam("id") int empno, Model model) {
		Employee e = repository.findById(empno).get();
		EmployeeModel emodel = new EmployeeModel();
		BeanUtils.copyProperties(e, emodel);
		model.addAttribute("emodel", emodel);
		return "editEmployee";

	}

	@PreAuthorize("hasRole('MANAGER')")
	@PostMapping("/updateEmployee")
	public String updateEmployee(@ModelAttribute("emodel") EmployeeModel emodel) {
		Employee e = new Employee();
		BeanUtils.copyProperties(emodel, e);
		repository.saveAndFlush(e);
		return "redirect:listEmployees";
	}

	// 2. SECURITY ANNOTATION ADDED
	@PreAuthorize("hasRole('MANAGER')")
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam("id") int empno) {
		repository.deleteById(empno);
		return "redirect:listEmployees";
	}

	@GetMapping("/loggedout")
	public String userLoggedOut() {
		return "logoutSuccess";
	}

	// 3. NEW METHOD ADDED FOR THE CUSTOM ACCESS DENIED PAGE
	@GetMapping("/access-denied")
	public String getAccessDeniedPage() {
		return "denied"; // Assumes you have a denied.jsp file
	}
}