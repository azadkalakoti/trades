package com.db.trade.assignment.model;

import java.util.Objects;

public class ServiceResponse {

    private ServiceResponseStatus status;
    private String code;

    public ServiceResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceResponseStatus status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ServiceResponse{" +
                "status=" + status +
                ", code='" + code + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceResponse that = (ServiceResponse) o;
        return status == that.status &&
                code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, code);
    }
}
