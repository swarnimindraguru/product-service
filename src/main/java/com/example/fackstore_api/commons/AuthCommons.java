package com.example.fackstore_api.commons;
import com.example.fackstore_api.dtos.UserDto;
import com.example.fackstore_api.exceptions.InvalidTokenException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class AuthCommons {
    private RestTemplate restTemplate;
    public AuthCommons(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    public UserDto validateTocken(String tokenValue){
        //Call userService to validate Token
        ResponseEntity<UserDto> responseEntity = restTemplate.getForEntity("http://localhost:8181/users/validate/"+ tokenValue, UserDto.class);
        if(responseEntity.getBody() == null){
            //token is invalid, throw some exception
            throw new InvalidTokenException("Invalid Token");
        }
        return responseEntity.getBody();
    }
}
