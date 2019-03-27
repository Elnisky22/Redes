public class RailFence {
	int depth;

	String encriptar(String plainText, int prof) throws Exception {
		int l = prof, c = plainText.length();
		char[][] mat = new char[l][c];
		int k = 0;
		char oi = '-';
		String cipherText = "";
		
		
		//Adiciona '-' em toda a matriz
		for(int i = 0; i < l; i++) {
			for(int j = 0; j < c; j++) {
				mat[i][j] = oi;
			}
		}
		
		//Insere a string na matriz para realizar a criptografia
		int sobe = 0, j = 0;
		for(int i = 0; i < c ; i++) {
			if(sobe == 0) {

				if(j == l-1) {
					mat[j][i] = plainText.charAt(k++);
					j--;
					i++;
					sobe = 1;
				}else{
					mat[j][i] = plainText.charAt(k++);
					j++;
				}
			}
			
			if(sobe != 0) {
				if(j == 0) {
					mat[j][i] = plainText.charAt(k++);
					sobe = 0;
					j++;
				}else {
					mat[j][i] = plainText.charAt(k++);
					j--;
				}
			}
		}

		//Inserir a string criptografada em cipherText 
		for (int i = 0; i < l; i++) {
			for (int m = 0; m < c; m++) {
				if(mat[i][m] != '-')
					cipherText += mat[i][m];
			}
		}
		
		System.out.println("\nEncriptando...");
		//Printar a matriz com a string criptografada
		for(int i = 0; i < l; i++) {
			for(int o = 0; o < c; o++) {
				System.out.print(mat[i][o]);
			}
			System.out.println("");
		}
		
		return cipherText;
	}

	String desencriptar(String cipherText, int prof) throws Exception {
		
		int c = cipherText.length(), l = prof;
		int i, j, k = -1, row = 0, col = 0, m = 0;
	    char[][] mat = new char[l][c];
	    String plainText = "";
	    
	    //Adiciona '-' em toda a matriz
	    for(i = 0; i < l; ++i)
	        for(j = 0; j < c; ++j)
	            mat[i][j] = '-';
	 
	    //Coloca '*' em forma de zig-zag
	    for(i = 0; i < c; ++i){
	        mat[row][col++] = '*';
	        if(row == 0 || row == l-1)
	            k= k * (-1);
	        row = row + k;
	    }
	 
	    //Substitui '*' pelos caracteres da String
	    for(i = 0; i < l; ++i)
	        for(j = 0; j < c; ++j)
	            if(mat[i][j] == '*')
	                mat[i][j] = cipherText.charAt(m++);
	 
	    row = col = 0;
	    k = -1;
	 
	    for(i = 0; i < c; ++i){
	    	plainText += mat[row][col++];
	 
	        if(row == 0 || row == l-1)
	            k = k * (-1);
	 
	        row = row + k;
	    }

		return plainText;
	}
}