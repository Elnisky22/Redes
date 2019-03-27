import java.util.Scanner;

public class RailFenceApp{
	
	public static void main(String args[])throws Exception{
		RailFence rf = new RailFence(); 
		Scanner scn = new Scanner(System.in);
        int prof;
        String mensagem, mensagemCifrada, mensagemDescif;

        System.out.println("Entre a mensagem:");
        mensagem = scn.nextLine();

        System.out.println("\nEntre a profundidade da encriptação:");
        prof = scn.nextInt();
        
        scn.close();

        mensagemCifrada = rf.encriptar(mensagem,prof);

        System.out.println("\nMensagem encriptada:\n" + mensagemCifrada);

        mensagemDescif = rf.desencriptar(mensagemCifrada, prof);
                 
        System.out.println("\nMensagem desencriptada:\n" + mensagemDescif);
   
	}
}