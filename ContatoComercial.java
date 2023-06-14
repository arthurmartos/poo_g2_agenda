public class ContatoComercial extends Contato {
    private String detalhes;
    private String cargo;
    private String empresa;
    private boolean favorito;

    public ContatoComercial(String nome, String numero, String detalhes, String cargo, String empresa, int idade, boolean favorito, String redesSociais, String observacoes) {
        super(nome, numero, detalhes, idade, favorito, redesSociais, observacoes);
        this.detalhes = detalhes;
        this.cargo = cargo;
        this.empresa = empresa;
        this.favorito = favorito;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public String getCargo() {
        return cargo;
    }

    public String getEmpresa() {
        return empresa;
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
               "\nCargo: " + getCargo() +
               "\nEmpresa: " + getEmpresa() +
               "\nFavorito: " + isFavorito() +
               "\nRedes Sociais: " + getRedesSociais() +
               "\nObservações: " + getObservacoes() +
               "\nTipo: Comercial\n"+
               "---------------------------------------";
    }
}
