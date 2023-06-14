public class ContatoPessoal extends Contato {
    private String detalhes;
    private boolean favorito;

    public ContatoPessoal(String nome, String numero, String detalhes, int idade, boolean favorito, String redesSociais, String observacoes) {
        super(nome, numero, detalhes, idade, favorito, redesSociais, observacoes);
        this.detalhes = detalhes;
        this.favorito = favorito;
    }
    
    public String getDetalhes() {
        return detalhes;
    }

    public boolean isFavorito() {
        return favorito;
    }

    @Override
    public String toString() {
        return "Nome: " + getNome() +
               "\nNúmero: " + getNumero() +
               "\nIdade: " + getIdade() +
               "\nDetalhes: " + getDetalhes() +
               "\nFavorito: " + isFavorito() +
               "\nRedes Sociais: " + getRedesSociais() +
               "\nObservações: " + getObservacoes() +
               "\nTipo: Pessoal\n" +
               "---------------------------------------";
    }
}
