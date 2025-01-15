import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Elenchi di nomi, cognomi e soprannomi
        List<String> nomi = leggiDaFile("nomi.txt");
        List<String> cognomi = leggiDaFile("cognomi.txt");
        List<String> soprannomi = leggiDaFile("soprannomi.txt");

        if (nomi.isEmpty() || cognomi.isEmpty() || soprannomi.isEmpty()) {
            System.out.println("Errore: uno o più file sono vuoti o non esistono.");
            return;
        }

        // Creazione della rubrica
        HashMap<String, String> rubrica = new HashMap<>();
        Random random = new Random();

        // Generazione di 1000 contatti con nome-cognome e soprannome casuali
        for (int i = 0; i < 1000; i++) {
            String nomeCompleto = nomi.get(random.nextInt(nomi.size())) + " " + cognomi.get(random.nextInt(cognomi.size()));
            String soprannome = soprannomi.get(random.nextInt(soprannomi.size()));
            rubrica.put(nomeCompleto, soprannome);
        }

        // Input dell'utente per filtrare soprannomi
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci l'inizio del soprannome da cercare: ");
        String filtro = scanner.nextLine();

        // Stampa dei contatti con soprannomi che iniziano con il filtro
        System.out.println("Contatti trovati:");
        for (Map.Entry<String, String> entry : rubrica.entrySet()) {
            if (entry.getValue().startsWith(filtro)) {
                System.out.println("Nome: " + entry.getKey() + ", Soprannome: " + entry.getValue());
            }
        }

        scanner.close();
    }

    // Metodo per leggere un file di testo e restituire una lista di stringhe
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
