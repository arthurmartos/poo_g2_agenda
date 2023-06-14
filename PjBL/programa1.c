#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Tabela.h"

#define TAMANHO 20

int main(int argc, char *argv[]) {

  confere_arquivos(argv[1],argv[2]);
  int qtde_linhas = TAMANHO;
  Loja* compradores;
  compradores = ler_txt(argv[1],&qtde_linhas);
  dados_para_binario(compradores,argv[2],qtde_linhas);
    
  return 0;
}
