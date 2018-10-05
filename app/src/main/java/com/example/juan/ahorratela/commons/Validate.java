package com.example.juan.ahorratela.commons;

import android.widget.EditText;

/**
 * clase diseñada para validar
 * Created by Matthew Seidel on 05/10/18.
 */

public class Validate {
    final double VERSION=1.0;

    /**
     * valida que lo textos estén vacíos
     *
     * @param texto cadena a validar
     * @return true si no está vacío false si está vacío
     * @deprecated
     */
    public static boolean validarTexto(String texto) {
        boolean bool = true;
        if (texto.isEmpty()) {
            bool = false;
        }
        if (texto == "") {
            bool = false;
        }
        if (texto == null) {
            bool = false;
        }
        return bool;
    }

    /**
     * valida que el edit text no esté vacío
     *
     * @param texto edit text a validar
     * @return texto ya validado
     * @throws Exception si el editText está vacío
     */
    public static String validarTexto(EditText texto) throws Exception {
        String text = texto.getText().toString();
        if (text.length() < 5) {
            texto.requestFocus();
            throw new Exception("los campos deben llevar más de 5 digitos");
        }
        if (text.isEmpty() || text.equals(" ")) {
            texto.requestFocus();
            throw new Exception("los campos deben llevar un valor");
        }
        return text;
    }
}
