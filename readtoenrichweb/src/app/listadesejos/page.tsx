"use client"

import { useState } from 'react';
import NavBar from "@/components/NavBar";

export default function ListaDesejos() {
  const [wishList, setWishList] = useState<string[]>([]);

  const addItemToWishList = () => {
    // Exemplo de adicionar um item fictício
    setWishList((prevItems: string[]) => [...prevItems, 'Novo Livro']);
  }

  return (
    <main className="flex min-h-screen flex-col items-center">
      <NavBar active="listadesejos" />
      <h1>Lista de Desejos</h1>
      <button onClick={addItemToWishList}>Adicionar Item</button>
      <div>
        {wishList.map((item, index) => (
          <p key={index}>{item}</p>
        ))}
      </div>
    </main>
  );
}
