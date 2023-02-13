package com.api.user.steps;

import com.api.user.domain.Address;
import com.api.user.domain.dtos.AddressDto;
import com.api.user.domain.dtos.UserDto;
import io.cucumber.java.es.Dado;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Entao;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostControllerStep {

    RestTemplate restTemplate = new RestTemplate();
    UserDto user = new UserDto();
    AddressDto address = new AddressDto();
    Integer port = 8082;
    String beginUrl = "http://localhost:";
    Integer response;

    @Dado("Quando tenho um usuario com nome {string}, CPF {string}, com endereço {string}, {int}, {string}, {string}, {string}")
    public void quando_tenho_um_usuario_com_nome_cpf_com_endereço(String string, String string2, String string3, Integer int1, String string4, String string5, String string6) {
        user.setName(string);
        user.setCpf(string2);
        address.setStreet(string3);
        address.setNumber(int1);
        address.setDistrict(string4);
        address.setCity(string5);
        address.setState(string6);
        user.setAddress(address);
    }
    @Quando("Faco um requisicao de cadastro")
    public void faco_um_requisicao_de_cadastro() {
        HttpEntity<Object> request = new HttpEntity<Object>(user);
        var res = restTemplate.exchange(beginUrl + port + "/user", HttpMethod.POST, request, String.class);
        response = res.getStatusCode().value();
    }
    @Entao("Devo receber o status {string}")
    public void devo_receber_o_status(String status) {
        assertEquals(status, String.valueOf(response));
    }



}
