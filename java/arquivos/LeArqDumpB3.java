package fast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LeArqDumpB3 {
	
	private static final Logger LOGGER = Logger.getLogger(LeArqDumpB3.class.getName());
	private static final String ARQ = "/home/tdso/kdi/workspaces/jee/Mock-B3/job_input_a.txt";

	public static void main(String[] args) throws IOException {
		
		leLinhaDado(); //ok
		//leArrayByte(); //ok
		//leDadosArrayChar(); // ok
		//leComBufferImprimeChar(); // ok
		// LeByteAByteImprimeChar(); // ok
		
	}
	
	// ok
	private static void leLinhaDado() {
		
		long timeStart = System.currentTimeMillis();
		System.out.println("Tempo start: " + timeStart);
		String linha = "";
		
		try {
			FileReader is = new FileReader(ARQ);
			BufferedReader br = new BufferedReader(is);
			linha = br.readLine();
			while (linha != null) {
				System.out.printf("%s\n", linha);
				linha = br.readLine();
			}
			is.close();

		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Erro na abertura/localizacao do arquivo");
			e.printStackTrace(System.out);
		} finally {
			
		}	
		long timeEnd = System.currentTimeMillis();
		System.out.println("Tempo end: " + timeEnd);
		System.out.println("Tempo total: " + (timeEnd-timeStart));
	}
	
	// ok
	private static void leDadosArrayChar() {
		
		long timeStart = System.currentTimeMillis();
		System.out.println("Tempo start: " + timeStart);
		StringBuilder texto = new StringBuilder();
		
		try {
			FileReader is = new FileReader(ARQ);
			
			char[] linha = new char[17];
						
			while (is.read(linha) != -1) {
				for (int j = 0; j<linha.length; j++) {
					System.out.print((char)linha[j]);
				}
				System.out.println();
			}
			is.close();

		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Erro na abertura/localizacao do arquivo");
			e.printStackTrace(System.out);
		} finally {
			
		}	
		System.out.println("Texto lido: " + texto);
		long timeEnd = System.currentTimeMillis();
		System.out.println("Tempo end: " + timeEnd);
		System.out.println("Tempo total: " + (timeEnd-timeStart));

	}
	
	// >> ok
	private static void leArrayByte() {
		
		long timeStart = System.currentTimeMillis();
		System.out.println("Tempo start: " + timeStart);
		
		try {
			InputStream is = new FileInputStream(ARQ);
			byte[] data_array_byte = new byte[17];
			
			while(is.read(data_array_byte) != -1) {
				for (int i=0; i < data_array_byte.length; i++) {
					System.out.print((char)data_array_byte[i]);
				}
			}
			System.out.println("");

			is.close();

		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Erro na abertura/localizacao do arquivo");
			e.printStackTrace(System.out);
		} finally {
			
		}		
		long timeEnd = System.currentTimeMillis();
		System.out.println("Tempo end: " + timeEnd);
		System.out.println("Tempo total: " + (timeEnd-timeStart));

		
	}
	// ok
	private static void leComBufferImprimeChar() {
		
		long timeStart = System.currentTimeMillis();
		System.out.println("Tempo start: " + timeStart);
		StringBuilder texto = new StringBuilder();
		
		try {
			// BufferedInputStream = armazenda 8192 bytes em buffer
			InputStream is = new BufferedInputStream(new FileInputStream(ARQ));
			
			int data_byte = is.read();
			while(data_byte != -1) {
				// o comando abaixo vai quebrar a linha pq vai ler o retorno de carro (enter)
				System.out.print((char)data_byte);
				texto.append((char)data_byte);
				data_byte = is.read();
			}
			System.out.println();
			is.close();

		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Erro na abertura/localizacao do arquivo");
			e.printStackTrace(System.out);
		} finally {
			
		}		
		System.out.println("Texto lido: ");
		System.out.println(texto);
		long timeEnd = System.currentTimeMillis();
		System.out.println("Tempo end: " + timeEnd);
		System.out.println("Tempo total: " + (timeEnd-timeStart));
	}
	
	// ok
	private static void LeByteAByteImprimeChar() {
		
		long timeStart = System.currentTimeMillis();
		System.out.println("Tempo start: " + timeStart);
		
		try {
			InputStream is = new FileInputStream(ARQ);
			
			int data_byte = is.read();
			while(data_byte != -1) {
				System.out.print((char)data_byte);
				data_byte = is.read();
			}
			System.out.println();
			is.close();

		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Erro na abertura/localizacao do arquivo");
			e.printStackTrace(System.out);
		} finally {
			
		}
		long timeEnd = System.currentTimeMillis();
		System.out.println("Tempo end: " + timeEnd);
		System.out.println("Tempo total: " + (timeEnd-timeStart));

	}

}
