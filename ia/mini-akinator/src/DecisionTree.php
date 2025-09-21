<?php
require_once __DIR__ . '/Node.php';

class DecisionTree
{
    private Node $root;
    private string $file;

    public function __construct(string $file)
    {
        if (file_exists($file)) {
            $data = json_decode(file_get_contents($file), true);
            $this->root = Node::fromArray($data);
        } else {
            $this->root = new Node("É um animal?");
            $this->root->yes = new Node("Cachorro");
            $this->root->no = new Node("Computador");
        }
        $this->file = $file;
    }

    public function play(): void
    {
        $this->root = $this->ask($this->root);
        $this->save();
    }

    private function ask(Node $node): Node
    {
        if ($node->isLeaf()) {
            echo "Você estava pensando em {$node->text}? (s/n): ";
            $answer = strtolower(trim(fgets(STDIN)));

            if ($answer === 's') {
                echo "Acertei! 🎉\n";
            } else {
                $node = $this->learn($node);
            }
            return $node;
        }

        echo $node->text . " (s/n): ";
        $answer = strtolower(trim(fgets(STDIN)));

        if (!in_array($answer, ['s', 'n'])) {
            echo "Resposta inválida. Por favor, responda com 's' ou 'n'.\n";
            return $this->ask($node);
        }

        if ($answer === 's') {
            $node->yes = $this->ask($node->yes);
        } else {
            $node->no = $this->ask($node->no);
        }

        return $node;
    }

    private function learn(Node $wrongNode): Node
    {
        echo "Qual era o personagem? ";
        $newCharacter = trim(fgets(STDIN));

        echo "Digite uma pergunta para diferenciar $newCharacter de {$wrongNode->text}: ";
        $newQuestion = trim(fgets(STDIN));

        echo "Para '$newCharacter', a resposta seria sim ou não? (s/n): ";
        $answer = strtolower(trim(fgets(STDIN)));

        $newNode = new Node($newQuestion);
        $newCharacterNode = new Node($newCharacter);

        if ($answer === 's') {
            $newNode->yes = $newCharacterNode;
            $newNode->no = $wrongNode;
        } else {
            $newNode->yes = $wrongNode;
            $newNode->no = $newCharacterNode;
        }

        echo "Entendido, aprendi algo novo! ✅\n";
        return $newNode;
    }

    private function save(): void
    {
        file_put_contents($this->file, json_encode($this->root, JSON_PRETTY_PRINT));
    }
}
