package com.github.seanv.gymtracker.services;


import com.github.seanv.gymtracker.dto.SetDto;
import com.github.seanv.gymtracker.mappers.SetMapper;
import com.github.seanv.gymtracker.repositories.SetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetService {

    private final SetRepository repository;
    private final SetMapper mapper;

    @Autowired
    public SetService(SetRepository repository, SetMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public SetDto getSet(Long id){
        return mapper.toDto(repository.findById(id).orElse(null));
    }
}
