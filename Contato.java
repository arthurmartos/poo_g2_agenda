import java.io.Serializable;

public abstract class Contato implements Serializable {
    private String nome;
    private String numero;
    private String detalhes;
    private int idade;
    private boolean favorito;
    private String redesSociais;
    private String observacoes;

    public Contato(String nome, String numero, String detalhes, int idade, boolean favorito, String redesSociais, String observacoes) {
        this.nome = nome;
        this.numero = numero;
        this.detalhes = detalhes;
        this.idade = idade;
        this.favorito = favorito;
        this.redesSociais = redesSociais;
        this.observacoes = observacoes;
    }

    public String getNome() {
        return nome;
    }

    public String getNumero() {
        return numero;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public int getIdade() {
        return idade;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public String getRedesSociais() {
        return redesSociais;
    }

    public String getObservacoes() {
        return observacoes;
    }

}
