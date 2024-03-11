# ReadToEnrich

## Requisitos

- [X] CRUD Livros
- [ ] CRUD Usuários
- [ ] CRUD MeusLivros
- [ ] Autenticação

## Endpoints

### CRUD Livros

`GET` /livro/buscar

Lista todos os livros cadastrados no sistema.

`200` sucesso

---

`GET` /livro/buscar/{id}

Retorna os detalhes de um livro com o `id` informado.

**códigos de status**

`200` sucesso
`404` id não encontrado

---

`POST` /livro/cadastrar

Cadastrar um novo livro.

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|-----------
|titulo|string|✅|O titulo do livro
|autor|string|✅|O nome do autor(s) do livro
|isbn|string|✅|Um identificador único para o livro
|categoria|string|✅|Um nome curto para identificar a categoria. Pré cadastrada no sistema.
|imagem|string|❌|Uma url que referencia a imagem do livro.

**códigos de status**

`201` criado com sucesso
`400` validação falhou

---

`DELETE` /livro/deletar/{id} 

Apaga o livro com o `id` informado.

**códigos de status**

`204` apagado com sucesso
`404` id não encontrado

---


`PUT` /livro/atualizar/{id} 

Altera o livro com o `id` informado.

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|-----------
|titulo|string|✅|O titulo do livro
|autor|string|✅|O nome do autor(s) do livro
|isbn|string|✅|Um identificador único para o livro
|categoria|string|✅|Um nome curto para identificar a categoria. Pré cadastrada no sistema.
|imagem|string|❌|Uma url que referencia a imagem do livro.

**códigos de status**

`200` sucesso
`404` id não encontrado
`400` validação falhou

---

**Schema**

```js
{
    "titulo": "Harry Potter e a Pedra Filosofal",
    "autor": "J.K. Rowling",
    "isbn": "1234567890",
    "categoria": "Ficção",
    "imagem": "https://lojaleiturinha.vtexassets.com/arquivos/ids/156464-800-800?v=638337749713470000&width=800&height=800&aspect=true"
}

```