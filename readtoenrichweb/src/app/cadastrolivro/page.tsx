"use client"

import React from 'react';
import { useState } from 'react';
import { Input, Button } from '@nextui-org/react';
import NavBar from "@/components/NavBar";

export default function CadastroLivro() {
  const [book, setBook] = useState({
    titulo: '',
    autor: '',
    isbn: '',
    categoria: '',
    imagem: ''
  });

  const handleSubmit = async (e: { preventDefault: () => void; }) => {
    e.preventDefault();
    const url = 'http://localhost:8080/livro';

    console.log(` LOG:: Book Data ${JSON.stringify(book)}`);

    try {
      const response = await fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(book),
      });

      // Logging the response status and status text
      console.log(` LOG:: Response status: ${response.status}, Status text: ${response.statusText}`);

      if (response.ok) {
        alert('Livro cadastrado com sucesso!');
        setBook({ titulo: '', autor: '', isbn: '', categoria: '', imagem: '' }); // Reset form
      } else {
        console.log('Response not OK:', response);
        throw new Error('Falha ao cadastrar livro');
      }
    } catch (error) {
      console.error('Erro ao cadastrar livro:', error);
      alert('Erro ao cadastrar livro!');
    }
  };

  const handleChange = (e: { target: { name: any; value: any; }; }) => {
    setBook({ ...book, [e.target.name]: e.target.value });
  };

  return (
    <main className="flex min-h-screen flex-col items-center">
      <NavBar active="cadastrolivro" />

      <section className="flex flex-col gap-4 bg-orange-100 min-w-[600px] mt-8 px-4 py-8 rounded items-center">
        <h1 className="text-amber-900 font-bold text-xl">Cadastrar Livro</h1>
        <form onSubmit={handleSubmit} className="flex flex-col gap-4 w-full">
          <Input label="Título" value={book.titulo} onChange={handleChange} name="titulo" />
          <Input label="Autor" value={book.autor} onChange={handleChange} name="autor" />
          <Input label="ISBN" value={book.isbn} onChange={handleChange} name="isbn" />
          <Input label="Categoria" value={book.categoria} onChange={handleChange} name="categoria" />
          <Input label="URL da Imagem" value={book.imagem} onChange={handleChange} name="imagem" />
          <Button color="primary" type="submit" className="w-full">Cadastrar Livro</Button>
        </form>
      </section>
    </main>
  );
}
