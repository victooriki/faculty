programa {
  const cadeia helloWord = "Ol� mundo!"
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

    escreva("Seu nome �: ", name, "\n")
    escreva("Sua idade �: ", idade, "\n")
    escreva("Sua altura �: ", altura, "\n")
  }
}
