"use client"
import React, { useState, useEffect } from 'react';
import DropdownActions from "@/components/DropDown";
import NavBar from "@/components/NavBar";
import { Input, Button } from "@nextui-org/react";
import { Search } from "lucide-react";

interface Categoria {
  id: number;
  nome: string;
  dataCriacao: string;
}

interface Livro {
  id: number;
  titulo: string;
  autor: string;
  isbn: string;
  categoria: Categoria;
  imagem: string;
}

export default function Home() {
  const [livros, setLivros] = useState<Livro[]>([]);
  const [filtro, setFiltro] = useState('');
  const [paginaAtual, setPaginaAtual] = useState(0);
  const [totalPaginas, setTotalPaginas] = useState(0);

  const fetchLivros = async (titulo?: string, pagina: number = 0) => {
    let url = `http://localhost:8080/livro?page=${pagina}`;
    if (titulo) {
      url += `&titulo=${encodeURIComponent(titulo)}`;
    }
    try {
      const response = await fetch(url);
      const data = await response.json();
      if (data.content && Array.isArray(data.content)) {
        setLivros(data.content);
        setTotalPaginas(data.totalPages);
        setPaginaAtual(data.number);
      } else {
        console.error("Expected 'content' array in the response, received:", data);
      }
    } catch (error) {
      console.error('Erro ao buscar livros:', error);
    }
  };

  useEffect(() => {
    fetchLivros(filtro);
  }, []);

  const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFiltro(e.target.value);
  };

  const handleFilter = () => {
    fetchLivros(filtro);
  };

  const handleNextPage = () => {
    if (paginaAtual < totalPaginas - 1) {
      fetchLivros(filtro, paginaAtual + 1);
    }
  };

  const handlePreviousPage = () => {
    if (paginaAtual > 0) {
      fetchLivros(filtro, paginaAtual - 1);
    }
  };

  return (
    <main className="flex min-h-screen flex-col items-center">
      <NavBar active="consultarlivros" />
      <section className="flex flex-col gap-4 bg-orange-100 min-w-[600px] mt-8 px-4 py-8 rounded items-center">
        <h2 className="text-amber-900 font-bold text-xl">Livros Cadastrados</h2>
        <div className="flex w-full flex-wrap md:flex-nowrap gap-8 py-4">
          <Input
            type="text"
            placeholder="Procurar nome do livro..."
            labelPlacement="outside"
            onChange={handleSearchChange}
            value={filtro}
            startContent={
              <button onClick={handleFilter} style={{ background: 'none', border: 'none' }}>
                <Search className="text-2xl text-default-400 cursor-pointer flex-shrink-0" />
              </button>
            }
          />
        </div>

        <table className="table-auto w-full mt-4 bg-orange-200 rounded">
          <thead>
            <tr className="text-left text-amber-900">
              <th className="px-4 py-2">Título</th>
              <th className="px-4 py-2">Autor</th>
              <th className="px-4 py-2">ISBN</th>
              <th className="px-4 py-2">Categoria</th>
              <th className="px-4 py-2">Ação</th>
            </tr>
          </thead>
          <tbody>
            {livros.map((livro, index) => (
              <tr key={index} className="bg-orange-100">
                <td className="px-4 py-2">{livro.titulo}</td>
                <td className="px-4 py-2">{livro.autor}</td>
                <td className="px-4 py-2">{livro.isbn}</td>
                <td className="px-4 py-2">{livro.categoria.nome}</td>
                <td className="px-4 py-2"><DropdownActions /></td>
              </tr>
            ))}
          </tbody>
        </table>
        <div className="flex justify-between w-full">
          <Button disabled={paginaAtual === 0} onPress={handlePreviousPage}>Anterior</Button>
          <Button disabled={paginaAtual >= totalPaginas - 1} onPress={handleNextPage}>Próxima</Button>
        </div>
      </section>
    </main>
  );
}
