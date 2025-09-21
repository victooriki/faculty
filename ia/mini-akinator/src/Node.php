<?php

class Node implements JsonSerializable
{
    public string $text;
    public ?Node $yes = null;
    public ?Node $no = null;

    public function __construct(string $text)
    {
        $this->text = $text;
    }

    public function isLeaf(): bool
    {
        return $this->yes === null && $this->no === null;
    }

    public function jsonSerialize(): mixed
    {
        return [
            'text' => $this->text,
            'yes' => $this->yes,
            'no' => $this->no,
        ];
    }

    public static function fromArray(array $data): Node
    {
        $node = new Node($data['text']);
        if ($data['yes']) {
            $node->yes = self::fromArray($data['yes']);
        }
        if ($data['no']) {
            $node->no = self::fromArray($data['no']);
        }
        return $node;
    }
}
