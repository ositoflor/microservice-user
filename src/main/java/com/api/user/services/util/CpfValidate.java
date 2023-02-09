package com.api.user.services.util;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.format.Formatter;
import br.com.caelum.stella.format.NITFormatter;

import java.util.InputMismatchException;

public class CpfValidate {

    public static boolean isValidCpf(String CPF) {
        String cpf = formattedCPF(CPF);

        if (cpf.equals("00000000000") ||
                cpf.equals("11111111111") ||
                cpf.equals("22222222222") || cpf.equals("33333333333") ||
                cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999") ||
                (cpf.length() != 11)){
            return(false);
        }

        try {
            return calcFristDigitCpf(cpf) && calcSecondDigitCpf(cpf);
        }catch (InputMismatchException error) {
            return false;
        }
    }

    public static String formattedCPF(String cpf) {
        Formatter formatter = new CPFFormatter();
        if (cpf.length() == 14){
            String formatted = formatter.unformat(cpf);
            return formatted;
        }
        return cpf;
    }

    public static boolean calcFristDigitCpf(String cpf){
        int fristDigit = cpf.charAt(9) - 48;
        int calc = 0;
        int weight = 10;

        for (int i = 0; i < 9; i++) {
            calc += (cpf.charAt(i) - 48) * weight;
            weight--;
        }
        calc = calc * 10 % 11;
        if (calc == fristDigit) return true;
        return false;
    }

    public static boolean calcSecondDigitCpf(String cpf){
        int secondDigit = cpf.charAt(10) - 48;
        int calc = 0;
        int weight = 11;

        for (int i = 0; i < 10; i++) {
            calc += (cpf.charAt(i) - 48) * weight;
            weight--;
        }
        calc = calc * 10 % 11;

        if (calc == secondDigit) return true;
        return false;
    }
}
