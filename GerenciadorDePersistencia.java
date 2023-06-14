import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GerenciadorDePersistencia {

    public List<Contato> carregarContatosPessoais(String caminho) throws IOException {
        List<Contato> contatos = new ArrayList<>();

        Path path = Paths.get(caminho);
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] partes = line.split(",");
                if (partes.length < 10) {
                    throw new IOException("Linha inválida: " + line);
                }

                String nome = partes[0];
                String numero = partes[1];
                String detalhes = partes[2];

                if (line.contains("Pessoal")) {
                    int idade;
                    try {
                        idade = Integer.parseInt(partes[5]);
                    } catch (NumberFormatException e) {
                        idade = 0;
                    }
                    boolean favorito = Boolean.parseBoolean(partes[6]);
                    String redesSociais = partes[7];
                    String observacoes = partes[8];

                    Contato contato = new ContatoPessoal(nome, numero, detalhes, idade, favorito, redesSociais, observacoes);
                    contatos.add(contato);
                } else if (line.contains("Comercial")) {
                    String empresa = partes[4];
                    int idade;
                    try {
                        idade = Integer.parseInt(partes[6]);
                    } catch (NumberFormatException e) {
                        idade = 0;
                    }
                    boolean favorito = Boolean.parseBoolean(partes[7]);
                    String redesSociais = partes[8];
                    String observacoes = partes[9];

                    Contato contato = new ContatoComercial(nome, numero, detalhes, empresa, observacoes, idade, favorito, redesSociais, observacoes);
                    contatos.add(contato);
                } else {
                    throw new IOException("Tipo de contato inválido: " + line);
                }
            }
        }
        return contatos;
    }

    
    public List<Contato> carregarContatosComerciais(String caminho) throws IOException {
        List<Contato> contatos = new ArrayList<>();

        Path path = Paths.get(caminho);
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] partes = line.split(",");
                if (partes.length < 10) {
                    throw new IOException("Linha inválida: " + line);
                }

                String nome = partes[0];
                String numero = partes[1];
                String detalhes = partes[2];
                String tipo = partes[partes.length - 1];

                if (tipo.equalsIgnoreCase("Comercial")) {
                    String empresa = partes[4];
                    String cargo = partes[3];
                    int idade;
                    try {
                        idade = Integer.parseInt(partes[5]);
                    } catch (NumberFormatException e) {
                        idade = 0;
                    }
                    boolean favorito = Boolean.parseBoolean(partes[6]);
                    String redesSociais = partes[7];
                    String observacoes = partes[8];

                    if (!cargo.trim().isEmpty()) {
                        Contato contato = new ContatoComercial(nome, numero, detalhes, cargo, empresa, idade, favorito, redesSociais, observacoes);
                        contatos.add(contato);
                    } else {
                        throw new IOException("Campo do cargo vazio: " + line);
                    }
                }
            }
        }
        return contatos;
    }
    
    public void salvarContato(Contato contato, String caminhoArquivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            StringBuilder sb = new StringBuilder();
            sb.append(contato.getNome()).append(",");
            sb.append(contato.getNumero()).append(",");
            sb.append(contato.getDetalhes()).append(",");
            if (contato instanceof ContatoComercial) {
                sb.append(((ContatoComercial) contato).getCargo()).append(",");
                sb.append(((ContatoComercial) contato).getEmpresa()).append(",");
            } else {
                sb.append(",,");
            }
            sb.append(contato.getIdade()).append(",");
            sb.append(contato.isFavorito()).append(",");
            sb.append(contato.getRedesSociais()).append(",");
            sb.append(contato.getObservacoes()).append(",");
            sb.append(contato instanceof ContatoComercial ? "Comercial" : "Pessoal");
            sb.append("\n");
            writer.write(sb.toString());
        }
    }
}