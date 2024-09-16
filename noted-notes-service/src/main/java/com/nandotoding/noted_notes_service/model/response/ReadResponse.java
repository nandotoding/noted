package com.nandotoding.noted_notes_service.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadResponse {
    private String id;
    private String accountId;
    private String title;
    private String note;
}
