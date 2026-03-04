package com.github.seanv.gymtracker.unit.services;

import com.github.seanv.gymtracker.dto.ProgramDto;
import com.github.seanv.gymtracker.entities.Program;
import com.github.seanv.gymtracker.mappers.ProgramMapper;
import com.github.seanv.gymtracker.repositories.ProgramRepository;
import com.github.seanv.gymtracker.services.ProgramService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProgramServiceUT {

    @Mock
    private ProgramRepository programRepository;

    @Mock
    ProgramMapper mapper;

    @InjectMocks
    private ProgramService programService;

    @Test
    void return_programs_list_when_getting_all_programs(){

        Program one = new Program(1L);
        Program two = new Program(2L);
        ProgramDto dtoOne = new ProgramDto(1L);
        ProgramDto dtoTwo = new ProgramDto(2L);
        List<Program> list = new ArrayList<>();
        list.add(one);
        list.add(two);

        when(programRepository.findAll()).thenReturn(list);
        when(mapper.toDto(one)).thenReturn(dtoOne);
        when(mapper.toDto(two)).thenReturn(dtoTwo);

        List<ProgramDto> dtos = programService.getAllPrograms();

        assertTrue(dtos.size() > 1);

    }


}
 /** @Mock mocks the selected class - provides a "fake" which you can use as if appl was really running

  @InjectMock Indicates which class in which you want the above Mock to be injected into
  */