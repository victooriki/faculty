programa {
  inclua biblioteca Matematica --> mat

  funcao inicio() {
    real valorLitro, qntdLitros, valorTotal

    escreva("Qual o valor do litro de gasolina? ")
    leia(valorLitro)

    escreva("Quantos litros você comprou? ")
    leia(qntdLitros)

    valorTotal = mat.arredondar(qntdLitros*valorLitro, 2)
    escreva("O valor a pagar é R$"+valorTotal)
  }
}
