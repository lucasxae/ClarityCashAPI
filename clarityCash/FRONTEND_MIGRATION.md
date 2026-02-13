# MIGRAÇÃO COMPLETA PARA NOVA API - REMOVER TODAS IMPLEMENTAÇÕES ANTIGAS

Preciso que você remova TODAS as implementações antigas e migre para usar EXCLUSIVAMENTE as rotas da API backend que foram implementadas.

## 🗑️ REMOVER COMPLETAMENTE:
- Qualquer implementação de autenticação existente no frontend
- Qualquer implementação de perfil de usuário existente
- Chamadas para APIs antigas ou externas
- Componentes, páginas ou formulários antigos
- Estados, contexts ou stores relacionados a auth/usuário
- Qualquer lógica implementada no frontend que deveria estar na API

## 🔄 IMPLEMENTAR COM AS NOVAS ROTAS:

### **Base URL:** `http://localhost:8080/api`

---

## 🔐 **AUTENTICAÇÃO** (`/api/auth`)

### **1. Login**
```
POST /api/auth/login
Body: {
  "email": "string",
  "password": "string"
}
Response: {
  "token": "string",
  "email": "string", 
  "name": "string"
}
```

### **2. Registro**
```
POST /api/auth/register
Body: {
  "name": "string",
  "email": "string",
  "password": "string"
}
Response: {
  "token": "string",
  "email": "string",
  "name": "string"
}
```

---

## 👤 **PERFIL DE USUÁRIO** (`/api/users`)
**⚠️ Todas as rotas precisam do header:** `Authorization: Bearer {token}`

### **1. Buscar Perfil do Usuário Logado**
```
GET /api/users/profile
Response: {
  "id": "uuid",
  "name": "string",
  "email": "string", 
  "objectives": "string",
  "salary": number,
  "financeDivisionType": "RULE_50_30_20" | "RULE_50_20_30" | "RULE_40_30_30" | "CUSTOM",
  "billsPercentage": number,
  "expensesPercentage": number,
  "investmentsPercentage": number
}
```

### **2. Atualizar Perfil do Usuário Logado**
```
PUT /api/users/profile
Body: {
  "name": "string" (opcional),
  "objectives": "string" (opcional),
  "salary": number (opcional),
  "financeDivisionType": "RULE_50_30_20" | "RULE_50_20_30" | "RULE_40_30_30" | "CUSTOM",
  "billsPercentage": number (apenas se CUSTOM),
  "expensesPercentage": number (apenas se CUSTOM),
  "investmentsPercentage": number (apenas se CUSTOM)
}
Response: {
  // mesmo formato do GET /profile
}
```

### **3. Listar Todos os Usuários**
```
GET /api/users
Response: [
  {
    // array com formato do GET /profile
  }
]
```

### **4. Deletar Perfil do Usuário Logado**
```
DELETE /api/users
Response: 204 No Content
```

---

## 📋 **REGRAS DE DIVISÃO FINANCEIRA:**
- **RULE_50_30_20**: 50% Contas, 30% Gastos, 20% Investimentos (automático)
- **RULE_50_20_30**: 50% Contas, 20% Gastos, 30% Investimentos (automático)
- **RULE_40_30_30**: 40% Contas, 30% Gastos, 30% Investimentos (automático)
- **CUSTOM**: Usuário define as porcentagens manualmente

---

## ✅ **IMPLEMENTAR NO FRONTEND:**

### **Autenticação:**
1. Tela de login com email/senha
2. Tela de registro com nome/email/senha
3. Armazenar token JWT no localStorage/sessionStorage
4. Interceptor para adicionar Authorization header automaticamente
5. Logout (limpar token)
6. Redirecionamento baseado em autenticação

### **Perfil de Usuário:**
1. Formulário de perfil com todos os campos
2. Seletor para tipos de divisão financeira com labels claras
3. Campos condicionais para porcentagens (apenas quando CUSTOM)
4. Validação: soma das porcentagens = 100% quando CUSTOM
5. Visualização das porcentagens calculadas automaticamente
6. Botão para deletar conta

### **Gerais:**
1. Tratamento de erros da API
2. Loading states
3. Validações de formulário
4. Navegação protegida por autenticação

---

## 🚨 **IMPORTANTE:**
- Use APENAS essas rotas - não implemente lógica que já existe na API
- O token JWT deve ser incluído em TODAS as requisições de usuário
- As porcentagens são calculadas automaticamente pela API nas regras pré-definidas
- Teste todas as rotas no Swagger: `http://localhost:8080/swagger-ui.html`
- **CORS configurado**: A API já permite requisições de qualquer origem

**REMOVA TUDO que não usa essas rotas e reimplemente do zero usando apenas esta API.**