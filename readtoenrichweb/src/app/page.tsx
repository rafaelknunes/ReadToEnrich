import DropdownActions from "@/components/DropDown";
import NavBar from "@/components/NavBar";
import {Input} from "@nextui-org/react";
import { Search } from "lucide-react";

export default function Home() {
  return (
    <main className="flex min-h-screen flex-col items-center">
      <NavBar active="consultarlivros"/>
      <section className="flex flex-col gap-4 bg-orange-100 min-w-[600px] mt-8 px-4 py-8 rounded items-center"> 
        <h2 className="text-amber-900	font-bold text-xl	">Livros Cadastrados</h2>
        <div className=" flex w-full flex-wrap md:flex-nowrap gap-8 py-4">
          <Input 
          type="pesquisar" 
          placeholder="procurar nome do livro..." 
          labelPlacement="outside"
          startContent={
          <Search className=" text-2xl text-default-400 pointer-events-none flex-shrink-0"/>
          }
        />
        </div>
          <div className="bg-orange-200 mt-8 px-4 py-4 rounded">
            <div className="flex justify-between flex-row gap-28 bg-orange-200 min-w-[600px] mt-8 px-2 rounded items-center">
              <h2 className="text-amber-900 text-base">Titulo</h2>
              <h2 className="text-amber-900 text-base">Autor</h2>
              <h2 className="text-amber-900 text-base">ISBN</h2>
              <h2 className="text-amber-900 text-base">Categoria</h2>
          </div>
            <hr className="my-2 border-amber-900" />
            <div className="flex justify-between flex-row gap-28 bg-orange-200 min-w-[600px] mt-8 px-2 rounded items-center">
              <h3 className="text-amber-900 text-xs	">Harry Potter</h3>
              <h3 className="text-amber-900 text-xs	">Jk Rolling</h3>
              <h3 className="text-amber-900 text-xs	">12346</h3>
              <h3 className="text-amber-900 text-xs	">Ficção</h3>
              <DropdownActions />
          </div>
        </div>
      </section>
    </main>
  );
}
