package com.nandotoding.noted_notes_service.controller;

import com.nandotoding.noted_notes_service.model.request.EditRequest;
import com.nandotoding.noted_notes_service.model.request.GetAllRequest;
import com.nandotoding.noted_notes_service.model.request.ReadRequest;
import com.nandotoding.noted_notes_service.model.request.WriteRequest;
import com.nandotoding.noted_notes_service.model.response.ReadResponse;
import com.nandotoding.noted_notes_service.model.response.SuccessResponse;
import com.nandotoding.noted_notes_service.model.response.WriteResponse;
import com.nandotoding.noted_notes_service.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotesController {
    private final NotesService notesService;

    @Autowired
    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @PostMapping("/notes/all")
    ResponseEntity getAll(@RequestBody GetAllRequest getAllRequest) {
        List<ReadResponse> readResponseList = notesService.getAll(getAllRequest);
        SuccessResponse<List<ReadResponse>> successResponse = new SuccessResponse<>();
        successResponse.setCode(HttpStatus.OK.value());
        successResponse.setStatus(HttpStatus.OK.name());
        successResponse.setData(readResponseList);
        return ResponseEntity.status(HttpStatus.OK).body(successResponse);
    }

    @PostMapping("/notes/write")
    ResponseEntity write(@RequestBody WriteRequest writeRequest) {
        WriteResponse writeResponse = notesService.write(writeRequest);
        SuccessResponse<WriteResponse> response = new SuccessResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setStatus(HttpStatus.OK.name());
        response.setData(writeResponse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/notes/read")
    ResponseEntity read(@RequestBody ReadRequest readRequest) {
        ReadResponse readResponse = notesService.read(readRequest);
        SuccessResponse<ReadResponse> successResponse = new SuccessResponse<>();
        successResponse.setCode(HttpStatus.OK.value());
        successResponse.setStatus(HttpStatus.OK.name());
        successResponse.setData(readResponse);
        return ResponseEntity.status(HttpStatus.OK).body(successResponse);
    }

    @PostMapping("/notes/edit")
    ResponseEntity edit(@RequestBody EditRequest editRequest) {
        WriteResponse editResponse = notesService.edit(editRequest);
        SuccessResponse<WriteResponse> successResponse = new SuccessResponse<>();
        successResponse.setCode(HttpStatus.OK.value());
        successResponse.setStatus(HttpStatus.OK.name());
        successResponse.setData(editResponse);
        return ResponseEntity.status(HttpStatus.OK).body(successResponse);
    }

    @PostMapping("/notes/delete")
    ResponseEntity delete(@RequestBody ReadRequest deleteRequest) {
        WriteResponse deleteResponse = notesService.delete(deleteRequest);
        SuccessResponse<WriteResponse> successResponse = new SuccessResponse<>();
        successResponse.setCode(HttpStatus.OK.value());
        successResponse.setStatus(HttpStatus.OK.name());
        successResponse.setData(deleteResponse);
        return ResponseEntity.status(HttpStatus.OK).body(successResponse);
    }
}
