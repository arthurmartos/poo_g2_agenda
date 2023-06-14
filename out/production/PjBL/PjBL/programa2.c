#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Tabela.h"


int main(int argc, char *argv[]){

  int op;

  while (1 == 1) {
    printf("Escolha uma opcao\n(1)Listar Compradores\n(2)Alterar valor do produto\n(3)Desconto no pagamento\n(4)Sair\nEscolha: ");
    if (scanf("%d", &op) == 1) {
      switch (op) {
      case 1:
        listar_dados(argv[1]);
        break;
      case 2:
        alterar_preco(argv[1]);
        break;
      case 3:
        desc_pagamento(argv[1]);
        break;
      case 4:
        bin_para_txt(argv[1],argv[2]);
        exit(0);
        break;
      default:
        printf("Opcão invalida!\n");
        break;
      }
    }
  }
}