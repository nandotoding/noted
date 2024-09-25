package com.nandotoding.noted_notes_service.service.implementation;

import com.nandotoding.noted_notes_service.exception.NotFoundException;
import com.nandotoding.noted_notes_service.exception.UnauthorizedException;
import com.nandotoding.noted_notes_service.model.entity.Notes;
import com.nandotoding.noted_notes_service.model.request.*;
import com.nandotoding.noted_notes_service.model.response.ReadResponse;
import com.nandotoding.noted_notes_service.model.response.WriteResponse;
import com.nandotoding.noted_notes_service.repository.NotesRepository;
import com.nandotoding.noted_notes_service.service.NotesService;
import com.nandotoding.noted_notes_service.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class NotesServiceImplementation implements NotesService {
    private final NotesRepository notesRepository;
    private final AuthUtil authUtil;

    @Autowired
    public NotesServiceImplementation(NotesRepository notesRepository, AuthUtil authUtil) {
        this.notesRepository = notesRepository;
        this.authUtil = authUtil;
    }

    @Override
    public List<ReadResponse> getAll(GetAllRequest getAllRequest) {
        AccountInfoRequest accountInfoRequest = new AccountInfoRequest(getAllRequest.getAccountId());

        if (!authUtil.isActiveSession(accountInfoRequest)) {
            throw new UnauthorizedException("Out of Session");
        }

        List<Notes> notesList = notesRepository.getAllNotes(getAllRequest.getAccountId());
        List<ReadResponse> readResponseList = new ArrayList<>();

        for (Notes n : notesList) {
            readResponseList.add(new ReadResponse(n.getId(), n.getAccountId(), n.getTitle(), n.getNote()));
        }

        return readResponseList;
    }

    @Override
    public WriteResponse write(WriteRequest writeRequest) {
        AccountInfoRequest accountInfoRequest = new AccountInfoRequest(writeRequest.getAccountId());

        if (!authUtil.isActiveSession(accountInfoRequest)) {
            throw new UnauthorizedException("Out of Session");
        }

        Notes notes = new Notes();
        notes.setId(UUID.randomUUID().toString());
        notes.setAccountId(writeRequest.getAccountId());
        notes.setWriteTime(OffsetDateTime.now(ZoneOffset.ofHours(7)));
        notes.setDeleted(false);
        notes.setTitle(writeRequest.getTitle());
        notes.setNote(writeRequest.getNote());
        notesRepository.save(notes);
        return new WriteResponse(notes.getId(), notes.getAccountId());
    }

    @Override
    public ReadResponse read(ReadRequest readRequest) {
        AccountInfoRequest accountInfoRequest = new AccountInfoRequest(readRequest.getAccountId());

        if (!authUtil.isActiveSession(accountInfoRequest)) {
            throw new UnauthorizedException("Out of Session");
        }

        Notes notes = notesRepository.getNote(readRequest.getId(), readRequest.getAccountId());

        if (notes == null) {
            throw new NotFoundException();
        }

        ReadResponse readResponse = new ReadResponse();
        readResponse.setId(notes.getId());
        readResponse.setAccountId(notes.getAccountId());
        readResponse.setTitle(notes.getTitle());
        readResponse.setNote(notes.getNote());
        return readResponse;
    }

    @Override
    public WriteResponse edit(EditRequest editRequest) {
        AccountInfoRequest accountInfoRequest = new AccountInfoRequest(editRequest.getAccountId());

        if (!authUtil.isActiveSession(accountInfoRequest)) {
            throw new UnauthorizedException("Out of Session");
        }

        Notes notes = notesRepository.getNote(editRequest.getId(), editRequest.getAccountId());

        if (notes == null) {
            throw new NotFoundException();
        }

        notesRepository.editNote(editRequest.getId(), editRequest.getAccountId(), editRequest.getTitle(), editRequest.getNote(), OffsetDateTime.now(ZoneOffset.ofHours(7)));
        return new WriteResponse(editRequest.getId(), editRequest.getAccountId());
    }

    @Override
    public WriteResponse delete(ReadRequest readRequest) {
        AccountInfoRequest accountInfoRequest = new AccountInfoRequest(readRequest.getAccountId());

        if (!authUtil.isActiveSession(accountInfoRequest)) {
            throw new UnauthorizedException("Out of Session");
        }

        Notes notes = notesRepository.getNote(readRequest.getId(), readRequest.getAccountId());

        if (notes == null) {
            throw new NotFoundException();
        }

        notesRepository.deleteNote(readRequest.getId(), readRequest.getAccountId());
        return new WriteResponse(readRequest.getId(), readRequest.getAccountId());
    }
}
