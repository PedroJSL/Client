package conector;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class criptico {
	private byte[] input, keyBytes = "arrocito".getBytes(), ivBytes = "soloocho".getBytes(); //ivBytes y keyBytes necesita ser de justo 8 caracteres,
	private SecretKeySpec key = new SecretKeySpec(keyBytes, "DES");
	private IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
	private Cipher cipher;
	private byte[] cipherText;
	private int ctLength;

	public String encriptar(String cadena) {
		try {
			input = cadena.getBytes();
			SecretKeySpec key = new SecretKeySpec(keyBytes, "DES");
			IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
			cipher = Cipher.getInstance("DES/CTR/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
			cipherText = new byte[cipher.getOutputSize(input.length)];
			ctLength = cipher.update(input, 0, input.length, cipherText, 0);
			ctLength += cipher.doFinal(cipherText, ctLength);

		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (ShortBufferException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return (new String(cipherText));
	}
	
	public String desencriptar(String cadena) {
		byte[] plainText = null;
		try {
			cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
			plainText = new byte[cipher.getOutputSize(ctLength)];
			int ptLenght = cipher.update(cipherText, 0,ctLength,plainText,0);
			ptLenght += cipher.doFinal(plainText, ptLenght);
						
		} catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ShortBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (new String(plainText));
	}

}
