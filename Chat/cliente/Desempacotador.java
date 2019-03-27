package cliente;

import java.io.ByteArrayOutputStream;

public class Desempacotador {

    public static String desempacotar(byte[] packet) {
        if (packet[0] == (byte)0x80) {
            int tamanho = (int) packet[1] << 8;
            tamanho += (int) packet[2];
            ByteArrayOutputStream dec = new ByteArrayOutputStream();
            dec.write(packet, 3, tamanho);
            return dec.toString();
        }
        return "erro protocolo invalido";
    }
}
