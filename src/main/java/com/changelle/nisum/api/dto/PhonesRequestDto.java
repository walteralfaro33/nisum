package com.changelle.nisum.api.dto;

import com.changelle.nisum.api.domain.model.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.stream.Collectors;

@Jacksonized
@Getter
@Builder
@AllArgsConstructor
public class PhonesRequestDto {
    private String number;
    private String cityCode;
    private String countryCode;

    public static PhonesRequestDto from(Phone phone) {
        return PhonesRequestDto.builder().number(phone.getNumber()).countryCode(phone.getCountryCode()).cityCode(phone.getCityCode()).build();
    }

    public static List<PhonesRequestDto> from(List<Phone> phones) {
        return phones.stream().map(PhonesRequestDto::from).collect(Collectors.toList());
    }
}
