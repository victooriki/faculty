package main

import (
    "bufio"
    "fmt"
    "os"
    "strconv"
    "strings"
)

func main() {
    file, err := os.Open("/etc/passwd")
    if err != nil {
        fmt.Println("Erro ao abrir /etc/passwd:", err)
        return
    }
    defer file.Close()

    fmt.Println("Contas de usuário no meu sistema Linux (Wsl):")
    scanner := bufio.NewScanner(file)

    for scanner.Scan() {
        line := scanner.Text()
        fields := strings.Split(line, ":")
        if len(fields) > 2 {
            username := fields[0]
            uid := fields[2]

            uidInt, err := strconv.Atoi(uid)
            if err != nil {
                continue
            }

            accountType := getAccountType(uidInt)

            if accountType != "" {
                fmt.Printf("Usuário: %s, Tipo de conta: %s\n", username, accountType)
            }
        }
    }

    if err := scanner.Err(); err != nil {
        fmt.Println("Erro ao ler o arquivo:", err)
    }
}

func getAccountType(uid int) string {
    if uid == 0 {
        return "Administrador (root)"
    } else if uid >= 1000 {
        return "Usuário comum"
    }

    return ""
}
