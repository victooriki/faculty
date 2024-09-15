programa {
  const cadeia helloWord = "Olá mundo!"
  cadeia name
  inteiro idade
  real altura

  funcao inicio() {
    escreva(helloWord, "\n")

    escreva("Digite o seu nome: ")
    leia(name)

    escreva("Digite a sua idade: ")
    leia(idade)

    escreva("Digite sua altura: ")
    leia(altura)

    escreva("Seu nome é: ", name, "\n")
    escreva("Sua idade é: ", idade, "\n")
    escreva("Sua altura é: ", altura, "\n")
  }
}
