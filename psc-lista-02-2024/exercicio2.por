programa {
  funcao inicio() {
    real numeroUm, numeroDois

    escreva("Digite o primeiro número: ")
    leia(numeroUm)

    escreva("Digite o segundo número: ")
    leia(numeroDois)

    escreva("A soma dos números é: ", soma(numeroUm, numeroDois), "\n")
    escreva("A subtração dos números é: ", subtracao(numeroUm, numeroDois), "\n")
    escreva("A multiplicação dos números é: ", multiplicacao(numeroUm, numeroDois), "\n")
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
