programa {
  inclua biblioteca Matematica --> mat

  funcao inicio() {
    real valorLitro, qntdLitros, valorTotal

    escreva("Qual o valor do litro de gasolina? ")
    leia(valorLitro)

    escreva("Quantos litros voc� comprou? ")
    leia(qntdLitros)

    valorTotal = mat.arredondar(qntdLitros*valorLitro, 2)
    escreva("O valor a pagar � R$"+valorTotal)
  }
}
