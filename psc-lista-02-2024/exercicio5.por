programa {
  inclua biblioteca Matematica --> mat
  
  funcao inicio() {
    real valorAlemanha, valorPortugal, valorItalia, valorTotalViagens
    inteiro qntdPessoasAlemanha, qntdPessoasPortugal, qntdPessoasItalia, totalPessoasViagens

    escreva("Qual o valor da viagem h� Alemanha? ")
    leia(valorAlemanha)

    escreva("Quantas pessoas ir�o viajar para Alemanha? ")
    leia(qntdPessoasAlemanha)

    escreva("Qual o valor da viagem h� Portugal? ")
    leia(valorPortugal)

    escreva("Quantas pessoas ir�o viajar para Portugal? ")
    leia(qntdPessoasPortugal)

    escreva("Qual o valor da viagem h� It�lia? ")
    leia(valorItalia)

    escreva("Quantas pessoas ir�o viajar para It�lia? ")
    leia(qntdPessoasItalia)

    valorTotalViagens = mat.arredondar((valorAlemanha*qntdPessoasAlemanha) + (valorPortugal*qntdPessoasPortugal) + (valorItalia*qntdPessoasItalia), 2)
    totalPessoasViagens = qntdPessoasAlemanha + qntdPessoasPortugal + qntdPessoasItalia

    escreva("O valor total das viagens para ", totalPessoasViagens, " pesssoas � R$", valorTotalViagens, "\n")
  }
}
