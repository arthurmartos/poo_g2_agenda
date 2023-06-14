#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Tabela.h"

#define TAMANHO 20


char *Produtos_str[10] = {"computados", "celular","fone","mouse","teclado","mousepad", "monitor", "controle","carregador", "cadeira"};



void confere_arquivos(char argv1[], char argv2[]){
  FILE * file_txt_inicial = fopen(argv1,"rt");
  if(file_txt_inicial){
      fclose(file_txt_inicial);
  }else{
    printf("1 argumento não confere");
  }

  FILE * file_bin = fopen(argv2,"wb");
  if(file_bin){
      fclose(file_bin);
  }else{
    printf("2 argumento não confere");
  }
}




Loja* ler_txt(char arq[],int*qtde_linhas){
    int MaisMemoria = TAMANHO;
    Loja *compradores = malloc(TAMANHO* sizeof(Loja));
    Loja estrut;
    int i = 0;
    FILE * file = fopen(arq, "rt");
    if(file){
        while(!feof(file)){
            if(fscanf(file, "%s %d %u %lf %d %lf",&*estrut.Nome,&estrut.Id,&estrut.Produto,&estrut.Preco,&estrut.Quantidade,&estrut.Pagamentos)){
            strcpy(compradores[i].Nome, estrut.Nome);
            compradores[i].Id = estrut.Id;
            compradores[i].Produto = estrut.Produto;
            compradores[i].Preco = estrut.Preco;
            compradores[i].Quantidade = estrut.Quantidade;
            compradores[i].Pagamentos = estrut.Pagamentos;
            i++;
            if(MaisMemoria == i){
                MaisMemoria = MaisMemoria + 1;
                const size_t NUMERO_BYTES = MaisMemoria;
                compradores = realloc(compradores, NUMERO_BYTES * sizeof(Loja));
            }  
        }
          }
    }else{
      printf("Erro ao abrir arquivo");
    }
    *qtde_linhas = MaisMemoria - 1;
    return compradores; 
}





void dados_para_binario(Loja* compradores,char arq[],int qtde_linhas){
  FILE *file = fopen(arq,"ab");
  if(file){
      fwrite(compradores, sizeof(Loja),qtde_linhas, file); 
  }else{
    printf("Erro ao abrir arquivo");
  } 
   fclose(file);
   printf("Binario salvo com sucesso");
}





void listar_dados(char arq[]) {
  FILE *file = fopen(arq, "rb");
  Loja lista;

  if (file) {
    while (!feof(file)) {
      if (fread(&lista, sizeof(Loja), 1, file)) {
        printf("Nome: %s\nIdentificador: %d\nProduto: %s\nPreco: %.2lf\nQuantidade:%d\nSaldo: %.2lf\n",lista.Nome, lista.Id, Produtos_str[lista.Produto],lista.Preco, lista.Quantidade, lista.Pagamentos);
        printf("-----------------------------------------------------------------------------------------\n");
      }
    }
  }
}





void alterar_preco(char arq[]) {
  FILE *file = fopen(arq, "rb+");
  Loja lista;
  int id, i = 1;
  if (file) {
    printf("\tLista de Produtos\n");
    printf("-----------------------------------------------------------------------------------------\n");
    while (fread(&lista, sizeof(Loja), 1, file)) {
      printf("identificador Produto: %d\nNome:%s\nIdentificador: %d\nPreco:%.2lf\n\n", i, lista.Nome, lista.Id, lista.Preco);
      i++;
      printf("-----------------------------------------------------------------------------------------\n");
    }
    printf("-----------------------------------------------------------------------------------------\n");
    printf("\nDigite o identificador do produto que deseja alterar o preco: ");
    if (scanf("%d", &id) == 1) {
      getchar();
      id--;
      if (id >= 0 && id < i - 1) {
        printf("Digite o novo preco do produto: ");
        fseek(file, id * sizeof(Loja), SEEK_SET);
        if (fread(&lista, sizeof(Loja), 1, file)){
          if (scanf("%lf", &lista.Preco) == 1) {
              fseek(file, id * sizeof(Loja), SEEK_SET);
              fwrite(&lista, sizeof(Loja), 1, file);
          }
        }
      }
      fclose(file);
    }
  } else {
    printf("Erro ao abrir o arquivo");
  }
}





void desc_pagamento(char arq[]) {
  double pagamento;
  FILE *file = fopen(arq, "rb+");
  Loja lista;
  int id, i = 1;
  if (file) {
    printf("\tLista de Produtos\n");
    printf("-----------------------------------------------------------------------------------------\n");
    while (fread(&lista, sizeof(Loja), 1, file)) {
      printf("identificador Produto: %d\nNome:%s\nIdentificador: %d\nPreco:%.2lf\n\n", i, lista.Nome, lista.Id, lista.Pagamentos);
      i++;
    }
    printf("-----------------------------------------------------------------------------------------\n");
    printf("\nDigite o identificador do produto que deseja realizar o pagamento:\n\n");
    if (scanf("%d", &id) == 1) {
      getchar();
      id--;
      if (id >= 0 && id < i - 1) {
        printf("Digite o valor a ser descontado: ");
        if ((scanf("%lf", &pagamento) == 1)) {
          fseek(file, id * sizeof(Loja), SEEK_SET);
          if (fread(&lista, sizeof(Loja), 1, file)) {
            lista.Pagamentos = lista.Pagamentos - pagamento;
            fseek(file, id * sizeof(Loja), SEEK_SET);
            fwrite(&lista, sizeof(Loja), 1, file);
          }
        }
      }
    }
    fclose(file);
  } else {
    printf("Erro ao abrir o arquivo");
  }
}






void bin_para_txt(char arq[], char saida[]){

  FILE *file = fopen(arq, "rb+");
  FILE *file_saida = fopen(saida, "at+");
  Loja dados;
  while (fread(&dados, sizeof(Loja), 1, file)) {
    fprintf(file_saida,"%s %.2lf %d %s %d %.2lf\n", dados.Nome, dados.Preco, dados.Id, Produtos_str[dados.Produto],dados.Quantidade,dados.Pagamentos);
  }
  fclose(file);
  fclose(file_saida);
}
