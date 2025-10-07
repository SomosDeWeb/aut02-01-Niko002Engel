package org.example;
import java.util.Objects;

public class Dni {
    private Character lDni;
    private long nDni;

    public Dni(char l, long n) {

        nDni = n;
        Character letraCalculada = letraValida(n);

        if ((l = Character.toUpperCase(l)) != letraCalculada){
            throw new RuntimeException("La letra del DNI es incorrecta. Debería ser " + letraCalculada);
        }

        lDni = l;
    }

    public static char letraValida(long n) {
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int resto = (int) (n % 23);
        return letras.charAt(resto);
    }

    @Override
    public String toString() {
        return nDni + String.valueOf(lDni);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Dni otroDni = (Dni) obj;
        return nDni == otroDni.nDni && lDni == otroDni.lDni;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nDni, lDni);
    }
}