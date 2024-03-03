programa {
  inclua biblioteca Matematica --> mat
  
  funcao inicio() {
    real valorAlemanha, valorPortugal, valorItalia, valorTotalViagens
    inteiro qntdPessoasAlemanha, qntdPessoasPortugal, qntdPessoasItalia, totalPessoasViagens

    escreva("Qual o valor da viagem há Alemanha? ")
    leia(valorAlemanha)

    escreva("Quantas pessoas irão viajar para Alemanha? ")
    leia(qntdPessoasAlemanha)

    escreva("Qual o valor da viagem há Portugal? ")
    leia(valorPortugal)

    escreva("Quantas pessoas irão viajar para Portugal? ")
    leia(qntdPessoasPortugal)

    escreva("Qual o valor da viagem há Itália? ")
    leia(valorItalia)

    escreva("Quantas pessoas irão viajar para Itália? ")
    leia(qntdPessoasItalia)

    valorTotalViagens = mat.arredondar((valorAlemanha*qntdPessoasAlemanha) + (valorPortugal*qntdPessoasPortugal) + (valorItalia*qntdPessoasItalia), 2)
    totalPessoasViagens = qntdPessoasAlemanha + qntdPessoasPortugal + qntdPessoasItalia

    escreva("O valor total das viagens para ", totalPessoasViagens, " pesssoas é R$", valorTotalViagens, "\n")
  }
}
