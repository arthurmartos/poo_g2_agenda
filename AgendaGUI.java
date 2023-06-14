import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;

public class AgendaGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Agenda");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JTextField caminhoArquivoTextField = new JTextField("C:\\Users\\gusta\\Downloads\\PjBL (1)\\PjBL\\lista.csv", 20);
        JButton carregarContatosPessoaisButton = new JButton("Carregar Contatos Pessoais");
        JButton carregarContatosComerciaisButton = new JButton("Carregar Contatos Comerciais");
        JButton criarContatoPessoalButton = new JButton("Criar Contato Pessoal");
        JButton criarContatoComercialButton = new JButton("Criar Contato Comercial");

        Agenda[] agendaArray = new Agenda[1];
        agendaArray[0] = new Agenda();
        GerenciadorDePersistencia gerenciador = new GerenciadorDePersistencia();

        carregarContatosPessoaisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String caminho = caminhoArquivoTextField.getText();
                try {
                    List<Contato> novosContatos = gerenciador.carregarContatosPessoais(caminho);
                    for (Contato novoContato : novosContatos) {
                        agendaArray[0].adicionarContatoSeNaoExistir(novoContato);
                    }
        
                    outputArea.setText("");
                    for (Contato contato : agendaArray[0].getContatos()) {
                        if (contato instanceof ContatoPessoal) {
                            outputArea.append(contato.toString() + "\n");
                        }
                    }
                    outputArea.append("Contatos pessoais carregados com sucesso.\n");
        
                    frame.pack();
                } catch (ExcecaoAgenda | IOException ex) {
                    outputArea.append("Erro ao carregar contatos pessoais: " + ex.getMessage() + "\n");
                }
            }
        });        

        carregarContatosComerciaisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String caminho = caminhoArquivoTextField.getText();
                try {
                    List<Contato> novosContatos = gerenciador.carregarContatosComerciais(caminho);
                    agendaArray[0].adicionarContatos(novosContatos);

                    outputArea.setText("");
                    for (Contato contato : novosContatos) {
                        outputArea.append(contato.toString() + "\n");
                    }
                    outputArea.append("Contatos comerciais carregados com sucesso.\n");

                    frame.pack();
                } catch (ExcecaoAgenda | IOException ex) {
                    outputArea.append("Erro ao carregar contatos comerciais: " + ex.getMessage() + "\n");
                }
            }
        });

        criarContatoPessoalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = JOptionPane.showInputDialog(frame, "Nome do contato pessoal:");
                    String numero = JOptionPane.showInputDialog(frame, "Número do contato pessoal:");
                    String detalhes = JOptionPane.showInputDialog(frame, "Detalhes do contato pessoal:");
                    int idade = Integer.parseInt(JOptionPane.showInputDialog(frame, "Idade do contato pessoal:"));
                    boolean favorito = Boolean.parseBoolean(JOptionPane.showInputDialog(frame, "É favorito? (true/false):"));
                    String redesSociais = JOptionPane.showInputDialog(frame, "Redes sociais do contato pessoal:");
                    String observacoes = JOptionPane.showInputDialog(frame, "Observações do contato pessoal:");


                    if (nome.isBlank() || numero.isBlank() || detalhes.isBlank() || redesSociais.isBlank() || observacoes.isBlank()) {
                        throw new IllegalArgumentException("Dados incorretos. Preencha todos os campos.");
                    }

                    Contato contato = new ContatoPessoal(nome, numero, detalhes, idade, favorito, redesSociais, observacoes);
                    agendaArray[0].adicionarContato(contato);

                    try {
                        gerenciador.salvarContato(contato, caminhoArquivoTextField.getText());
                    } catch (IOException ex) {
                        outputArea.append("Erro ao salvar contato pessoal: " + ex.getMessage() + "\n");
                    }

                    outputArea.append("Contato pessoal criado:\n" + contato.toString() + "\n");

                    frame.pack();
                } catch (ExcecaoAgenda ex) {
                    outputArea.append("Erro ao criar contato pessoal: " + ex.getMessage() + "\n");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Dados incorretos", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        criarContatoComercialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = JOptionPane.showInputDialog(frame, "Nome do contato comercial:");
                    String numero = JOptionPane.showInputDialog(frame, "Número do contato comercial:");
                    String detalhes = JOptionPane.showInputDialog(frame, "Detalhes do contato comercial:");
                    String cargo = JOptionPane.showInputDialog(frame, "Cargo do contato comercial:");
                    String empresa = JOptionPane.showInputDialog(frame, "Empresa do contato comercial:");
                    int idade = Integer.parseInt(JOptionPane.showInputDialog(frame, "Idade do contato comercial:"));
                    boolean favorito = Boolean.parseBoolean(JOptionPane.showInputDialog(frame, "É favorito? (true/false):"));
                    String redesSociais = JOptionPane.showInputDialog(frame, "Redes sociais do contato comercial:");
                    String observacoes = JOptionPane.showInputDialog(frame, "Observações do contato comercial:");

                    if (nome.isBlank() || numero.isBlank() || detalhes.isBlank() || cargo.isBlank() || empresa.isBlank() ||
                            redesSociais.isBlank() || observacoes.isBlank()) {
                        throw new IllegalArgumentException("Dados incorretos. Preencha todos os campos.");
                    }

                    Contato contato = new ContatoComercial(nome, numero, detalhes, cargo, empresa, idade, favorito, redesSociais, observacoes);
                    agendaArray[0].adicionarContato(contato);

                    try {
                        gerenciador.salvarContato(contato, caminhoArquivoTextField.getText());
                    } catch (IOException ex) {
                        outputArea.append("Erro ao salvar contato pessoal: " + ex.getMessage() + "\n");
                    }
                    
                    outputArea.append("Contato comercial criado:\n" + contato.toString() + "\n");


                    frame.pack(); // Redimensiona a janela de acordo com o novo conteúdo
                } catch (ExcecaoAgenda ex) {
                    outputArea.append("Erro ao criar contato comercial: " + ex.getMessage() + "\n");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Dados incorretos", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(carregarContatosPessoaisButton);
        buttonPanel.add(carregarContatosComerciaisButton);
        buttonPanel.add(criarContatoPessoalButton);
        buttonPanel.add(criarContatoComercialButton);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(caminhoArquivoTextField, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER); //

        frame.add(panel);
        frame.setVisible(true);
    }
}
