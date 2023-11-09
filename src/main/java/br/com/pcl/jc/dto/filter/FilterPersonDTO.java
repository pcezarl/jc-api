package br.com.pcl.jc.dto.filter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class FilterPersonDTO {

    private Optional<String> eTicket = Optional.empty();
    private Optional<String> name = Optional.empty();
    private Optional<String> email = Optional.empty();
    private Optional<String> documentNumber = Optional.empty();

}
