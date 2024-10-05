package com.nandotoding.noted_notes_service.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WriteResponse {
    private String id;
    private String accountId;
}
