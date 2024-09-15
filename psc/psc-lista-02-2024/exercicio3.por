programa {
  funcao inicio() {
    real notaUm, notaDois, notaTres, notaQuatro, media

    escreva("Digite sua primeira nota: ")
    leia(notaUm)

    escreva("Digite sua segunda nota: ")
    leia(notaDois)

    escreva("Digite sua terceira nota: ")
    leia(notaTres)

    escreva("Digite sua quarta nota: ")
    leia(notaQuatro)

    media = (notaUm + notaDois + notaTres + notaQuatro) /4

    escreva("A média das suas notas é: "+media)
  }
}
