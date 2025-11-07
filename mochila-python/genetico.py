import random
import argparse
import math
import os
from typing import List, Tuple, Dict

class Item:
    def __init__(self, name: str, weight: float, value: float):
        self.name = name
        self.weight = float(weight)
        self.value = float(value)

    def __repr__(self):
        return f"Item({self.name}, w={self.weight}, v={self.value})"

def read_instance(path: str) -> Tuple[float, List[Item]]:
    if not os.path.exists(path):
        raise FileNotFoundError(f"Arquivo não encontrado: {path}")
    items = []
    with open(path, 'r', encoding='utf-8') as f:
        lines = [ln.strip() for ln in f if ln.strip() != "" and not ln.strip().startswith("#")]
    if len(lines) < 2:
        raise ValueError("Formato de instância inválido (esperava pelo menos 2 linhas).")
    capacity = float(lines[0])
    for line in lines[2:]:
        parts = [p.strip() for p in line.split(',')]
        if len(parts) < 3:
            continue
        name, weight, value = parts[0], parts[1], parts[2]
        items.append(Item(name, weight, value))
    return capacity, items

def generate_initial_population(num_genes: int, population_size: int) -> List[List[int]]:
    population = []
    for _ in range(population_size):
        individual = [random.randint(0, 1) for _ in range(num_genes)]
        population.append(individual)
    return population

def evaluate_solution(solution: List[int], items: List[Item]) -> Tuple[float, float]:
    total_value = 0.0
    total_weight = 0.0
    for bit, item in zip(solution, items):
        if bit == 1:
            total_value += item.value
            total_weight += item.weight
    return total_value, total_weight

def fitness_function(solution: List[int],
                     items: List[Item],
                     capacity: float,
                     fitness_mode: str = "value",
                     penalty_coef: float = None) -> float:

    total_value, total_weight = evaluate_solution(solution, items)

    if fitness_mode == "value":
        fitness_raw = total_value
    elif fitness_mode == "ratio":
        if total_weight <= 0:
            fitness_raw = 0.0
        else:
            fitness_raw = total_value / total_weight
    else:
        raise ValueError("fitness_mode inválido. Use 'value' ou 'ratio'.")

    if total_weight <= capacity:
        return fitness_raw
    else:
        if penalty_coef is None:
            ratios = [(it.value / it.weight) if it.weight > 0 else 0.0 for it in items]
            penalty_coef = max(ratios) if ratios else 1.0
        over = total_weight - capacity
        penalized = fitness_raw - penalty_coef * over
        return penalized

def tournament_selection(population: List[List[int]],
                         items: List[Item],
                         capacity: float,
                         tournament_size: int = 3,
                         fitness_mode: str = "value") -> List[int]:
    tournament = random.sample(population, min(tournament_size, len(population)))
    best = max(tournament, key=lambda ind: fitness_function(ind, items, capacity, fitness_mode))
    return best.copy()

def roulette_selection(population: List[List[int]],
                       items: List[Item],
                       capacity: float,
                       fitness_mode: str = "value") -> List[int]:
    fitnesses = [fitness_function(ind, items, capacity, fitness_mode) for ind in population]
    min_f = min(fitnesses)
    if min_f <= 0:
        offset = abs(min_f) + 1e-6
        fitnesses = [f + offset for f in fitnesses]
    total = sum(fitnesses)
    if total == 0:
        return random.choice(population).copy()
    pick = random.uniform(0, total)
    cum = 0.0
    for ind, f in zip(population, fitnesses):
        cum += f
        if cum >= pick:
            return ind.copy()
    return population[-1].copy()

def crossover(parent1: List[int], parent2: List[int], crossover_rate: float) -> Tuple[List[int], List[int]]:
    size = len(parent1)
    if random.random() > crossover_rate or size < 2:
        return parent1.copy(), parent2.copy()
    point = random.randint(1, size - 1)
    child1 = parent1[:point] + parent2[point:]
    child2 = parent2[:point] + parent1[point:]
    return child1, child2

def mutate(individual: List[int], mutation_rate: float):
    for i in range(len(individual)):
        if random.random() < mutation_rate:
            individual[i] = 1 - individual[i]

def genetic_algorithm_knapsack(items: List[Item],
                               capacity: float,
                               population_size: int = 50,
                               crossover_rate: float = 0.8,
                               mutation_rate: float = 0.01,
                               num_generations: int = 200,
                               selection_method: str = "tournament",
                               tournament_size: int = 3,
                               elitism_size: int = 1,
                               fitness_mode: str = "value",
                               penalty_coef: float = None,
                               seed: int = None) -> Dict:
    if seed is not None:
        random.seed(seed)

    num_genes = len(items)
    population = generate_initial_population(num_genes, population_size)

    best_history = []
    best_solution = None
    best_fitness = -math.inf

    for gen in range(num_generations):
        evaluated = [(ind, fitness_function(ind, items, capacity, fitness_mode, penalty_coef)) for ind in population]
        evaluated_sorted = sorted(evaluated, key=lambda x: x[1], reverse=True)
        current_best, current_best_fitness = evaluated_sorted[0][0], evaluated_sorted[0][1]

        if current_best_fitness > best_fitness:
            best_fitness = current_best_fitness
            best_solution = current_best.copy()

        best_history.append(best_fitness)

        next_generation = [ind.copy() for ind, f in evaluated_sorted[:elitism_size]]

        while len(next_generation) < population_size:
            if selection_method == "tournament":
                p1 = tournament_selection(population, items, capacity, tournament_size, fitness_mode)
                p2 = tournament_selection(population, items, capacity, tournament_size, fitness_mode)
            else:
                p1 = roulette_selection(population, items, capacity, fitness_mode)
                p2 = roulette_selection(population, items, capacity, fitness_mode)

            c1, c2 = crossover(p1, p2, crossover_rate)

            mutate(c1, mutation_rate)
            if len(next_generation) < population_size:
                next_generation.append(c1)
            if len(next_generation) < population_size:
                mutate(c2, mutation_rate)
                next_generation.append(c2)

        population = next_generation

    final_eval = [(ind, fitness_function(ind, items, capacity, fitness_mode, penalty_coef)) for ind in population]
    final_best = max(final_eval, key=lambda x: x[1])[0]
    final_value, final_weight = evaluate_solution(final_best, items)

    return {
        "best_solution": best_solution,
        "best_fitness": best_fitness,
        "final_best_individual": final_best,
        "final_value": final_value,
        "final_weight": final_weight,
        "history": best_history
    }

def print_solution(solution: List[int], items: List[Item]):
    total_value, total_weight = evaluate_solution(solution, items)
    print("Solução encontrada:")
    for i, bit in enumerate(solution):
        if bit == 1:
            it = items[i]
            print(f"  - {it.name}: peso={it.weight}, valor={it.value}")
    print(f"Peso total: {total_weight}")
    print(f"Valor total: {total_value}")

def parse_args():
    parser = argparse.ArgumentParser(description="Algoritmo Genético - Problema da Mochila")
    parser.add_argument("instance", help="Arquivo de instância (formato esperado no enunciado).")
    parser.add_argument("--pop", type=int, default=80, help="Tamanho da população")
    parser.add_argument("--gens", type=int, default=300, help="Número de gerações")
    parser.add_argument("--cx", type=float, default=0.8, help="Taxa de cruzamento")
    parser.add_argument("--mut", type=float, default=0.02, help="Taxa de mutação por gene")
    parser.add_argument("--sel", choices=["tournament", "roulette"], default="tournament", help="Método de seleção")
    parser.add_argument("--tourn", type=int, default=3, help="Tamanho do torneio (se seleção torneio)")
    parser.add_argument("--elit", type=int, default=1, help="Tamanho do elitismo")
    parser.add_argument("--mode", choices=["value", "ratio"], default="value", help="Modo de fitness")
    parser.add_argument("--seed", type=int, default=None, help="Semente RNG (opcional)")
    return parser.parse_args()

def main():
    args = parse_args()
    capacity, items = read_instance(args.instance)
    print(f"Instância lida: capacidade={capacity}, itens={len(items)}")
    result = genetic_algorithm_knapsack(
        items=items,
        capacity=capacity,
        population_size=args.pop,
        crossover_rate=args.cx,
        mutation_rate=args.mut,
        num_generations=args.gens,
        selection_method=args.sel,
        tournament_size=args.tourn,
        elitism_size=args.elit,
        fitness_mode=args.mode,
        seed=args.seed
    )

    best = result["best_solution"]
    final_best = result["final_best_individual"]
    print("Melhor solução (fitness penalizado):")
    print_solution(best, items)
    print("\nMelhor indivíduo final (última geração):")
    print_solution(final_best, items)

    hist_path = "history.txt"
    with open(hist_path, "w", encoding='utf-8') as f:
        for i, val in enumerate(result["history"]):
            f.write(f"{i}\t{val}\n")
    print(f"\nHistórico salvo em: {hist_path}")

    import matplotlib.pyplot as plt
    plt.figure()
    plt.plot(result['history'])
    plt.title('Evolução do melhor fitness por geração')
    plt.xlabel('Geração')
    plt.ylabel('Melhor fitness (acumulado)')
    plt.grid(True)
    plt.savefig('fitness_history.png')
    print('Plot salvo em fitness_history.png')

if __name__ == "__main__":
    main()
