package br.com.pcl.jc.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(Include.NON_NULL)
@ToString
public class PersonDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonProperty(value="id")
    private Long id;
    @JsonProperty(value="buy_status")
    private String buyStatus;
    @JsonProperty(value="buyer_name")
    private String buyerName;
    @JsonProperty(value="buyer_mail")
    private String buyerEmail;
    @JsonProperty(value="buyer_document_number")
    private String buyerDocumentNumber;
    @JsonProperty(value="buyer_phone")
    private String buyerPhone;
    @JsonProperty(value="checkin_concluded")
    private String checkinConcluded;
    @JsonProperty(value="date_checkin")
    private String dateCheckin;
    @JsonProperty(value="name")
    private String name;
    @JsonProperty(value="email")
    private String email;
    @JsonProperty(value="document_number")
    private String documentNumber;
    @JsonProperty(value="zipcode")
    private String zipcode;
    @JsonProperty(value="address")
    private String address;
    @JsonProperty(value="city")
    private String city;
    @JsonProperty(value="state")
    private String state;
    @JsonProperty(value="country")
    private String country;
    @JsonProperty(value="phone")
    private String phone;
    @JsonProperty(value="birthdate")
    private String birthdate;
    @JsonProperty(value="cellphone")
    private String cellphone;
    @JsonProperty(value="batch")
    private String batch;
    @JsonProperty(value="purchased_at")
    private String purchased_at;
    @JsonProperty(value="confirmed_at")
    private String confirmed_at;
    @JsonProperty(value="blocked_eticket")
    private String blockedEticket;

    private String eTicket;

    private boolean checked;

    private String checkedBy;

    private LocalDateTime checkedAt;

}
