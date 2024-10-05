package com.nandotoding.noted_notes_service.service;

import com.nandotoding.noted_notes_service.model.request.EditRequest;
import com.nandotoding.noted_notes_service.model.request.GetAllRequest;
import com.nandotoding.noted_notes_service.model.request.ReadRequest;
import com.nandotoding.noted_notes_service.model.request.WriteRequest;
import com.nandotoding.noted_notes_service.model.response.ReadResponse;
import com.nandotoding.noted_notes_service.model.response.WriteResponse;

import java.util.List;

public interface NotesService {
    List<ReadResponse> getAll(GetAllRequest getAllRequest);
    WriteResponse write(WriteRequest writeRequest);
    ReadResponse read(ReadRequest readRequest);
    WriteResponse edit(EditRequest editRequest);
    WriteResponse delete(ReadRequest readRequest);
}
