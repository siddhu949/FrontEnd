package com.book.dto;

import lombok.Data;

@Data
public class LoginResponseDto {
 

    private boolean loggedIn;
    private ResponseStatus responseStatus;

    // Getter for loggedIn
    public boolean isLoggedIn() {
        return loggedIn;
    }

    // Setter for loggedIn
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    // Getter for responseStatus
    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    // Setter for responseStatus
    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    // toString() method
    @Override
    public String toString() {
        return "LoginResponseDto{" +
                "loggedIn=" + loggedIn +
                ", responseStatus=" + responseStatus +
                '}';
    }

    // equals() method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginResponseDto that = (LoginResponseDto) o;
        return loggedIn == that.loggedIn &&
                responseStatus == that.responseStatus;
    }

    // hashCode() method
    @Override
    public int hashCode() {
        return java.util.Objects.hash(loggedIn, responseStatus);
    }
}
