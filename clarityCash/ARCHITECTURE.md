# ClarityCash API - Clean Architecture

## Estrutura do Projeto

```
src/main/java/com/example/clarityCash/
├── auth/                           # Módulo de Autenticação
│   ├── domain/
│   │   ├── entity/                 # Entidades de negócio (User, Role, etc.)
│   │   └── usecase/                # Casos de uso (LoginUseCase, RegisterUseCase)
│   ├── application/
│   │   ├── service/                # Serviços de aplicação
│   │   └── dto/                    # DTOs de entrada/saída
│   └── infrastructure/
│       ├── controller/             # Controllers REST
│       ├── repository/             # Repositórios JPA
│       └── config/                 # Configurações específicas do auth
│
├── finance/                        # Módulo Financeiro
│   ├── domain/
│   │   ├── entity/                 # Entidades (Transaction, Account, Category)
│   │   └── usecase/                # Casos de uso financeiros
│   ├── application/
│   │   ├── service/                # Serviços de aplicação
│   │   └── dto/                    # DTOs financeiros
│   └── infrastructure/
│       ├── controller/             # Controllers REST
│       ├── repository/             # Repositórios JPA
│       └── config/                 # Configurações específicas
│
├── shared/                         # Módulo Compartilhado
│   ├── domain/
│   │   ├── entity/                 # Entidades base (BaseEntity)
│   │   ├── usecase/                # Interfaces de casos de uso
│   │   └── exception/              # Exceções customizadas
│   ├── application/
│   │   ├── service/                # Serviços compartilhados
│   │   └── dto/                    # DTOs base
│   └── infrastructure/
│       ├── config/                 # Configurações globais
│       ├── util/                   # Utilitários
│       └── security/               # Configurações de segurança
│
└── config/                         # Configurações Globais
    ├── database/                   # Configuração do banco
    ├── security/                   # Configuração de segurança
    └── web/                        # Configuração web
```

## Camadas da Clean Architecture

### 1. Domain (Núcleo)
- **Entities**: Regras de negócio fundamentais
- **Use Cases**: Casos de uso específicos da aplicação
- **Não depende de nada externo**

### 2. Application 
- **Services**: Orquestração dos casos de uso
- **DTOs**: Objetos de transferência de dados
- **Depende apenas do Domain**

### 3. Infrastructure
- **Controllers**: Pontos de entrada da API
- **Repositories**: Acesso a dados
- **Config**: Configurações técnicas
- **Depende de Application e Domain**

## Benefícios para MVP

1. **Desenvolvimento Rápido**: Estrutura clara facilita implementação
2. **Testabilidade**: Cada camada pode ser testada independentemente  
3. **Manutenibilidade**: Mudanças isoladas por responsabilidade
4. **Escalabilidade**: Fácil adição de novos módulos
5. **Flexibilidade**: Troca de tecnologias sem impacto no negócio

## Próximos Passos

1. Implementar entidades do domain
2. Criar casos de uso
3. Implementar services da application
4. Criar controllers e repositories
5. Configurar segurança e banco de dados