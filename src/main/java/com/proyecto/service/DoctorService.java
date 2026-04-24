package com.proyecto.service;

import com.proyecto.domain.Doctor;
import com.proyecto.repository.DoctorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    
    private final DoctorRepository doctorRepository;
    
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
    
    @Transactional(readOnly = true)
    public List<Doctor> getDoctores(boolean activos) {
        return activos ? doctorRepository.findByActivoTrue() : doctorRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Optional<Doctor> getDoctor(Integer idDoctor) {
        return doctorRepository.findById(idDoctor);
    }
    
    @Transactional
    public Doctor save(Doctor doctor) {
        if (doctor.getActivo() == null) {
            doctor.setActivo(true);
        }
        return doctorRepository.save(doctor);
    }
    
    @Transactional
    public void delete(Integer idDoctor) {
        doctorRepository.deleteById(idDoctor);
    }
}