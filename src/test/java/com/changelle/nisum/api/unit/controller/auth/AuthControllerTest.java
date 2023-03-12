package com.changelle.nisum.api.unit.controller.auth;

import com.changelle.nisum.api.dto.CreateUserRequestDto;
import com.changelle.nisum.api.dto.CreateUserResponseDto;
import com.changelle.nisum.api.dto.PhonesRequestDto;
import com.changelle.nisum.api.security.model.JwtRequest;
import com.changelle.nisum.api.security.model.JwtResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@Configuration
public class AuthControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    int randomServerPort = 8080;

    @Test
    public void test_whenGetToken_ok() throws Exception {
        HttpHeaders httpHeaders = getHttpHeaders();
        Assert.assertNotNull(httpHeaders.get("Authorization"));
    }

    @Test
    public void test_whenGetToken_error() throws Exception {
        JwtRequest jwt = new JwtRequest();
        HttpEntity<JwtRequest> entity = new HttpEntity<>(jwt, null);

        ResponseEntity<JwtResponse> response = restTemplate.exchange(
                new URI("http://localhost:" + randomServerPort + "/api-nisum/v1/authenticate"),
                HttpMethod.POST,
                entity,
                JwtResponse.class);

        Assert.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }


    @Test
    public void test_whenAuth_api_post() throws Exception {
        ParameterizedTypeReference<CreateUserResponseDto> responseType = new ParameterizedTypeReference<>() {
        };

        List<PhonesRequestDto> phonesRequestDtoList = List.of(PhonesRequestDto.builder().cityCode("1").countryCode("2").number("11232323").build());
        CreateUserRequestDto createUserRequestDto = CreateUserRequestDto.builder().name("walter").email("waltera2SS2@gmail.com").password("aasasAsa8ad5").phones(phonesRequestDtoList).build();

        ResponseEntity<CreateUserResponseDto> response = restTemplate.exchange(
                new URI("http://localhost:" + randomServerPort + "/api-nisum/v1/user"),
                HttpMethod.POST,
                new HttpEntity<>(createUserRequestDto, getHttpHeaders()),
                responseType);

        assertThat(response.getStatusCodeValue()).isEqualTo(HttpServletResponse.SC_OK);
        assertThat(response.getBody()).isNotNull();
    }

    private HttpHeaders getHttpHeaders() throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + getToken());
        return headers;
    }

    private String getToken() throws URISyntaxException {
        JwtRequest jwt = new JwtRequest();
        jwt.setUsername("admin");
        jwt.setPassword("password");
        HttpEntity<JwtRequest> entity = new HttpEntity<>(jwt, null);

        ResponseEntity<JwtResponse> response = restTemplate.exchange(
                new URI("http://localhost:" + randomServerPort + "/api-nisum/v1/authenticate"),
                HttpMethod.POST,
                entity,
                JwtResponse.class);

        return Objects.requireNonNull(response.getBody()).getJwtToken();
    }
}
