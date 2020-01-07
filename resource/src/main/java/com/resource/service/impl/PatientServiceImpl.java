package com.resource.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.resource.domain.Patient;
import com.resource.domain.enums.Gender;
import com.resource.domain.enums.Status;
import com.resource.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService{

	public List<Patient> findAll() {
        List<Patient> patientList = new ArrayList<Patient>();
        Patient patient1 = new Patient();
        patient1.setPatientId(1L);
        patient1.setFirstName("Ben");
        patient1.setLastName("Franklin");
        patient1.setGender(Gender.MALE);
        patient1.setMedicalOrder("Medicine1");
        patient1.setDosage("7 x 10ml");
        patient1.setStatus(Status.ACTIVE);
        patientList.add(patient1);

        Patient patient2 = new Patient();
        patient2.setPatientId(2L);
        patient2.setFirstName("John");
        patient2.setLastName("Adams");
        patient2.setGender(Gender.MALE);
        patient2.setMedicalOrder("Medicine2");
        patient2.setDosage("12 x 5ml");
        patient2.setStatus(Status.ACTIVE);
        patientList.add(patient2);

        return patientList;
    }
}
