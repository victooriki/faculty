programa {
  funcao inicio() {
    real numeroUm, numeroDois

    escreva("Digite o primeiro n�mero: ")
    leia(numeroUm)

    escreva("Digite o segundo n�mero: ")
    leia(numeroDois)

    escreva("A soma dos n�meros �: ", soma(numeroUm, numeroDois), "\n")
    escreva("A subtra��o dos n�meros �: ", subtracao(numeroUm, numeroDois), "\n")
    escreva("A multiplica��o dos n�meros �: ", multiplicacao(numeroUm, numeroDois), "\n")
  }

  funcao real soma(real numeroUm, real numeroDois) {
    real resultado = numeroUm+numeroDois
    retorne resultado
  }

  funcao real subtracao(real numeroUm, real numeroDois) {
    real resultado = numeroUm-numeroDois
    retorne resultado
  }

  funcao real multiplicacao(real numeroUm, real numeroDois) {
    real resultado = numeroUm*numeroDois
    retorne resultado
  }

}
