package com.emsi.meteo.app.ws.requests;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
@Data
public class ContactRequest {
    private String mobile;
    private String skype;
}
