#ifndef TABELA_H
#define TABELA_H
#include <stdio.h>
#include <stdlib.h>
#include <string.h>




enum Produtos_e {
  computados,
  celular,
  fone,
  mouse,
  teclado,
  mousepad,
  monitor,
  controle,
  carregador,
  cadeira
};




typedef struct {
  char Nome[30];
  enum Produtos_e Produto;
  int Id;
  double Preco;
  int Quantidade;
  double Pagamentos;
} Loja;




void confere_arquivos(char argv1[], char argv2[]);
Loja* ler_txt(char arq[],int*qtde_linhas);
void dados_para_binario(Loja* compradores,char arq[],int qtde_linhas);
int opcoes_usuario(char argv_binario[],char argv_saida[]);
void listar_dados(char arq[]);
void alterar_preco(char arq[]);
void desc_pagamento(char arq[]);
void bin_para_txt(char arq[], char saida[]);



#endif