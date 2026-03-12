package com.github.seanv.gymtracker.unit.services;

import com.github.seanv.gymtracker.dto.ProgramDayDto;
import com.github.seanv.gymtracker.dto.ProgramDayExerciseDto;
import com.github.seanv.gymtracker.dto.ProgramDto;
import com.github.seanv.gymtracker.entities.Program;
import com.github.seanv.gymtracker.entities.ProgramDay;
import com.github.seanv.gymtracker.entities.ProgramDayExercise;
import com.github.seanv.gymtracker.exception.type.ProgramNotFoundException;
import com.github.seanv.gymtracker.mappers.ProgramMapper;
import com.github.seanv.gymtracker.repositories.ProgramRepository;
import com.github.seanv.gymtracker.services.ProgramDayService;
import com.github.seanv.gymtracker.services.ProgramService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProgramServiceUnitTest {

    @Mock
    private ProgramRepository programRepository;

    @Mock
    private ProgramMapper mapper;

    @Mock
    private ProgramDayService programDayService;

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

    @Test
    void when_getting_program_by_id_return_valid_program_dto(){

        ProgramDto dto = new ProgramDto(1L);
        Program one = new Program(1L);
        List<ProgramDayDto> programDaysDto = new ArrayList<>();

        when(programRepository.findById(1L)).thenReturn(Optional.of(one));
        when(mapper.toDto(one)).thenReturn(dto);
        when(programDayService.getProgramDaysByProgramId(1L)).thenReturn(programDaysDto);
        when(programService.getProgramDays(1L)).thenReturn(programDaysDto);

        var result = programService.getProgram(1L);

        assertEquals(dto, result);
    }

    @Test
    void when_invalid_id_supplied_then_throw_correct_exception(){


        when(programRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ProgramNotFoundException.class, () -> programService.getProgram(999L));
    }

    @Test
    void when_creating_program_return_saved_program(){

        Program program = new Program();

        ProgramDay programDay1 = new ProgramDay();
        programDay1.setProgramDayExercises(List.of(new ProgramDayExercise(), new ProgramDayExercise()));
        ProgramDay programDay2 = new ProgramDay();
        programDay2.setProgramDayExercises(List.of(new ProgramDayExercise(), new ProgramDayExercise()));

        program.setProgramDays(List.of(programDay1, programDay2));

        when(programRepository.save(any())).thenReturn(program);

        var result = programService.createProgram(program);

    }

}
 /** @Mock mocks the selected class - provides a "fake" which you can use as if appl was really running

  @InjectMock Indicates which class in which you want the above Mock to be injected into
  */