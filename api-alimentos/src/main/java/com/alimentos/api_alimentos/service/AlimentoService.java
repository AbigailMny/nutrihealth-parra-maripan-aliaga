package com.alimentos.api_alimentos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alimentos.api_alimentos.model.Alimento;
import com.alimentos.api_alimentos.repository.AlimentoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AlimentoService {

    @Autowired
    private AlimentoRepository alimentoRepository;

    public List<Alimento> mostrarAlimento(){
        return alimentoRepository.findAll();
    }

    public Alimento buscarId(Long id){
        return alimentoRepository.findById(id).get();
    }

    public Alimento crearAlimento(Alimento alimento){
        return alimentoRepository.save(alimento);
    }

    public void eliminarAlimento(Long id){
        alimentoRepository.deleteById(id);
    }


}
