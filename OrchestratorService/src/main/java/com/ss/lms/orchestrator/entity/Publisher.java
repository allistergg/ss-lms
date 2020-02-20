package com.ss.lms.orchestrator.entity;

import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Publisher {

    private Integer publisherId;
    @NotNull
    @Size(min=1, max=45, message="Publisher name must be between 1 and 45 characters")
    private String publisherName;
    @Size(min=1, max=45, message="Publisher address must be between 1 and 45 characters" )
    private String publisherAddress;
    @Size(min=1, max=45, message="Publisher address must be between 1 and 45 characters") 
    String publisherPhone;

    
    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublisherAddress() {
        return publisherAddress;
    }

    public void setPublisherAddress(String publisherAddress) {
        this.publisherAddress = publisherAddress;
    }

    public String getPublisherPhone() {
        return publisherPhone;
    }

    public void setPublisherPhone(String publisherPhone) {
        this.publisherPhone = publisherPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return getPublisherId().equals(publisher.getPublisherId()) &&
                getPublisherName().equals(publisher.getPublisherName()) &&
                Objects.equals(getPublisherAddress(), publisher.getPublisherAddress()) &&
                Objects.equals(getPublisherPhone(), publisher.getPublisherPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPublisherId(), getPublisherName(), getPublisherAddress(), getPublisherPhone());
    }
}
