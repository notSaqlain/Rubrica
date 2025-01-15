import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        
        List<String> nomi = leggiDaFile("Nomi.txt");
        List<String> cognomi = leggiDaFile("Cognomi.txt");
        List<String> soprannomi = leggiDaFile("Soprannomi.txt");

        if (nomi.isEmpty() || cognomi.isEmpty() || soprannomi.isEmpty()) {
            System.out.println("Errore: uno o più file sono vuoti o non esistono.");
            return;
        }

        
        HashMap<String, String> rubrica = new HashMap<>();
        Random random = new Random();

        
        for (int i = 0; i < 1000; i++) {
            String nomeCompleto = nomi.get(random.nextInt(nomi.size())) + " " + cognomi.get(random.nextInt(cognomi.size()));
            String soprannome = soprannomi.get(random.nextInt(soprannomi.size()));
            rubrica.put(nomeCompleto, soprannome);
        }

        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci l'inizio del soprannome da cercare: ");
        String filtro = scanner.nextLine();

        
        System.out.println("Contatti trovati:");
        for (Map.Entry<String, String> entry : rubrica.entrySet()) {
            if (entry.getValue().startsWith(filtro)) {
                System.out.println("Nome: " + entry.getKey() + ", Soprannome: " + entry.getValue());
            }
        }

        scanner.close();
    }

    
    private static List<String> leggiDaFile(String nomeFile) {
        List<String> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeFile))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lista.add(linea.trim());
            }
        } catch (IOException e) {
            System.out.println("Errore durante la lettura del file: " + nomeFile);
        }
        return lista;
    }
}
