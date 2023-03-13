package com.api.user.service;

import static org.junit.Assert.*;
import com.api.user.domain.Address;
import com.api.user.domain.User;
import com.api.user.domain.dtos.AddressDto;
import com.api.user.domain.dtos.UserDto;
import com.api.user.exceptionhandler.MessageExceptionHandler;
import com.api.user.exceptionhandler.excptions.CPFExistsException;
import com.api.user.exceptionhandler.excptions.CPFInvalidException;
import com.api.user.repositories.UserRepository;
import com.api.user.services.UserService;
import com.api.user.services.util.CpfValidate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class ServiceTest {

    private UserDto usuarioValido;
    private UserDto usarioCPFInvalido;
    private UserDto usarioCPFExistente;

    private AddressDto enderecoValido;

    private User user = mock(User.class);

    private UserDto userDto = mock(UserDto.class);

    @BeforeEach
    void setUp() {
        enderecoValido = new AddressDto();
        enderecoValido.setStreet("Rua Teste");
        enderecoValido.setNumber(117);
        enderecoValido.setComplement("teste");
        enderecoValido.setDistrict("Teste bairro");
        enderecoValido.setCity("Teste Cidade");
        enderecoValido.setState("PE");

        usuarioValido = new UserDto();
        usuarioValido.setName("Teste");
        usuarioValido.setCpf("524.467.190-15");
        usuarioValido.setAddress(enderecoValido);


        usarioCPFInvalido = new UserDto();
        usarioCPFInvalido.setName("Teste");
        usarioCPFInvalido.setCpf("11122233345");
        usarioCPFInvalido.setAddress(enderecoValido);

        usarioCPFExistente = new UserDto();
        usarioCPFExistente.setName("Teste");
        usarioCPFExistente.setCpf("524.467.190-15");
        usarioCPFExistente.setAddress(enderecoValido);
    }

    @Mock
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    public void testNewUserValid() {
        User user1 = new User();
        Address address = new Address();
        var cpfValidate = CpfValidate.isValidCpf(usuarioValido.getCpf());
        assertTrue(cpfValidate);

        when(userRepository.existsByCpf(ArgumentMatchers.eq(usuarioValido.getCpf()))).thenReturn(false);

        User response = userService.save(usuarioValido);
        BeanUtils.copyProperties(usuarioValido, user1);
        BeanUtils.copyProperties(enderecoValido, address);
        user1.setId(1);
        user1.setAddress(address);
        address.setIdAddress(1);
        assertEquals(user1, response)e
    }

    @Test
    public void testNewUserCPFInvalid() {
        var cpfValidate = CpfValidate.isValidCpf(usarioCPFInvalido.getCpf());
        assertTrue(!cpfValidate);

        assertThrows(CPFInvalidException.class, () -> userService.save(usarioCPFInvalido));
    }

    @Test
    public void testNewUserCPFExisting() {
        var cpfValidate = CpfValidate.isValidCpf(usarioCPFExistente.getCpf());
        assertTrue(cpfValidate);

        when(userRepository.existsByCpf(ArgumentMatchers.eq(usarioCPFExistente.getCpf()))).thenReturn(true);
        userService.save(usarioCPFExistente);

        assertThrows(CPFExistsException.class, () -> userService.save(usarioCPFExistente));
    }
}
