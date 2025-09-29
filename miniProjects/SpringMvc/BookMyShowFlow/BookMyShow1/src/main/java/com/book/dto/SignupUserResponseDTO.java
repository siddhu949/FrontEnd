package com.book.dto;
import lombok.Data;
@Data
public class SignupUserResponseDTO {
    private ResponseStatus responseStatus;
    private String name;
    private String email;
    private int userId;

    // Getters and Setters
    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // toString()
    @Override
    public String toString() {
        return "SignupUserResponseDTO{" +
                "responseStatus=" + responseStatus +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", userId=" + userId +
                '}';
    }

    // equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignupUserResponseDTO that = (SignupUserResponseDTO) o;
        return userId == that.userId &&
                responseStatus == that.responseStatus &&
                java.util.Objects.equals(name, that.name) &&
                java.util.Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(responseStatus, name, email, userId);
    }

	public void setMessage(String string) {
		// TODO Auto-generated method stub
		
	}
}
