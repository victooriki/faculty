<?php
require_once __DIR__ . '/DecisionTree.php';

class MiniAkinator
{
    public function run(): void
    {
        echo "=== Mini Akinator (PHP CLI) ===\n";
        $tree = new DecisionTree(__DIR__ . '/../data.json');
        $tree->play();
    }
}
