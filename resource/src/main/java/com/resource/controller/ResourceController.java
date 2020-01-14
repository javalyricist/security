package com.resource.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.resource.domain.Patient;
import com.resource.service.PatientService;

@RestController
public class ResourceController {

	@Autowired
	private PatientService patientService;

	@GetMapping("/resource/patient/all")
	public ModelAndView findAll(ModelAndView model) {
		model.addObject("patientService", patientService.findAll());
		model.setViewName("AppwaleContentScreen");
		return model;
	}
	@GetMapping("/resource/patient/test")
	public List<Patient> test(ModelAndView model) {
		return patientService.findAll();
	}
}
