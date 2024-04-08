import Link from "next/link";

interface NavBarProps{
  active: "consultarlivros" | "cadastrolivro" | "listadesejos" | "reviewlivro"
}

export default function NavBar(props:NavBarProps){
   const {active} = props
   const classActive = "text-xs	border-orange-100 border-b-2 pb-2"
  
  return(

    <nav className="flex w-full justify-between items-center px-8 py-2 bg-amber-900	">
        <div className="overflow-hidden">
          <img className= "w-18 h-20"src="Logo_Segurança_Residencial_com_Águia_Minimalista__1_-removebg-preview.png" alt="avatar usuário" />
        </div>
        <ul className=" flex gap-40">
          <li className={active == "consultarlivros" ? classActive: ""}><Link href="/">Consultar Livro</Link></li>
          <li className={active == "cadastrolivro" ? classActive: ""}><Link href="/cadastrolivro">Cadastro Livros</Link></li>
          <li className={active == "listadesejos" ? classActive: ""}><Link href="/listadesejos">Lista de Desejos</Link></li>
          <li className={active == "reviewlivro" ? classActive: ""}><Link href="/reviewlivro">Review de Livros</Link></li>
        </ul>
        <div className="w-14 h-14 rounded-full overflow-hidden ">
          <img src="https://i.pravatar.cc/300" alt="avatar usuário" />
        </div>
    </nav>



    )
}