package com.nandotoding.noted_notes_service.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountInfoResponse {
    private String id;
    private boolean isActive;
    private boolean isDeleted;
}
