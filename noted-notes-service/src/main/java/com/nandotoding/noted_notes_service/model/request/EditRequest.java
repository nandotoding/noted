package com.nandotoding.noted_notes_service.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditRequest {
    private String id;
    private String accountId;
    private String title;
    private String note;
}
