"use client";

import React, { useState } from 'react';
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
  const [message, setMessage] = useState('');
  const [isError, setIsError] = useState(false);

  const handleSubmit = async (e: { preventDefault: () => void; }) => {
    e.preventDefault();
    const url = 'http://localhost:8080/livro';

    try {
      const response = await fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(book),
      });

      console.log(`Response status: ${response.status}, Status text: ${response.statusText}`);

      if (response.ok) {
        setMessage('Livro cadastrado com sucesso!');
        setIsError(false);
        setBook({ titulo: '', autor: '', isbn: '', categoria: '', imagem: '' }); // Reset form
        setTimeout(() => setMessage(''), 3000); // Clear message after 3 seconds
      } else {
        setMessage('Falha ao cadastrar livro');
        setIsError(true);
        setTimeout(() => setMessage(''), 3000); // Clear message after 3 seconds
      }
    } catch (error) {
      console.error('Erro ao cadastrar livro:', error);
      setMessage('Erro ao cadastrar livro!');
      setIsError(true);
      setTimeout(() => setMessage(''), 3000); // Clear message after 3 seconds
    }
  };

  const handleChange = (e: { target: { name: any; value: any; }; }) => {
    const { name, value } = e.target;
    setBook((prevBook) => ({
      ...prevBook,
      [name]: value
    }));
  };

  return (
    <main className="flex min-h-screen flex-col items-center">
      <NavBar active="cadastrolivro" />

      <section className="flex flex-col gap-4 bg-orange-100 min-w-[600px] mt-8 px-4 py-8 rounded items-center">
        {message && (
          <div className={`px-4 py-2 rounded text-white ${isError ? 'bg-red-500' : 'bg-green-500'}`}>
            {message}
          </div>
        )}
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
