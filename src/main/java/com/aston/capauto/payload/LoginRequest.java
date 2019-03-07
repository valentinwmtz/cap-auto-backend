package com.aston.capauto.payload;

import javax.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank
    private String usernameOremail;

    @NotBlank
    private String password;

    public LoginRequest() {
    }

    public String getUsernameOremail() {
        return usernameOremail;
    }

    public void setUsernameOremail(String usernameOremail) {
        this.usernameOremail = usernameOremail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
