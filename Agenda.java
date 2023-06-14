    import java.io.*;
    import java.util.ArrayList;
    import java.util.List;

    public class Agenda implements Serializable {
        private List<Contato> contatos;

        public Agenda() {
            contatos = new ArrayList<>();
        }

        public void adicionarContato(Contato contato) throws ExcecaoAgenda {
            if (contato.getNome() == null || contato.getNumero() == null) {
                throw new ExcecaoAgenda("Contato inválido");
            }
            contatos.add(contato);
        }

        public void adicionarContatos(List<Contato> novosContatos) throws ExcecaoAgenda {
            for (Contato contato : novosContatos) {
                if (contato.getNome() == null || contato.getNumero() == null) {
                    throw new ExcecaoAgenda("Contatos inválidos");
                }
            }
            contatos.addAll(novosContatos);
        }

        public List<Contato> getContatos() {
            return new ArrayList<>(contatos);
        }
        
        public String getContatosAsString() {
            StringBuilder sb = new StringBuilder();
            for (Contato contato : contatos) {
                sb.append("Nome: ").append(contato.getNome()).append(", ");
                sb.append("Número: ").append(contato.getNumero()).append(", ");
                sb.append("Detalhes: ").append(contato.getDetalhes()).append("\n");
            }
            return sb.toString();
        }

        public void adicionarContatoSeNaoExistir(Contato novoContato) throws ExcecaoAgenda {
            if (novoContato.getNome() == null || novoContato.getNumero() == null) {
                throw new ExcecaoAgenda("Contato inválido");
            }
            boolean existe = false;
            for (Contato contato : contatos) {
                if (contato.getNome().equals(novoContato.getNome()) && contato.getNumero().equals(novoContato.getNumero())) {
                    existe = true;
                    break;
                }
            }
            if (!existe) {
                contatos.add(novoContato);
            }
        }
    }
