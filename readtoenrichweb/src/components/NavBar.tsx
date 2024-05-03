import Link from "next/link";

interface NavBarProps {
  active: "consultarlivros" | "cadastrolivro" | "listadesejos" | "reviewlivro"
}

export default function NavBar(props: NavBarProps) {
  const { active } = props;
  const classActive = "text-base font-medium text-white border-b-2 border-orange-300 pb-1";
  const classInactive = "text-base font-medium text-amber-100 hover:text-white hover:border-orange-300 hover:border-b-2 pb-1";

  return (
    <nav className="flex w-full justify-between items-center px-8 py-1 bg-amber-900">
      <div className="flex items-center h-full">
        <img className="w-16 h-auto" src="logo.png" alt="logo read to enrich" />
      </div>
      <ul className="flex items-center gap-8">
        <li className={active === "consultarlivros" ? classActive : classInactive}>
          <Link href="/">Consultar Livro</Link>
        </li>
        <li className={active === "cadastrolivro" ? classActive : classInactive}>
          <Link href="/cadastrolivro">Cadastro Livros</Link>
        </li>
        <li className={active === "listadesejos" ? classActive : classInactive}>
          <Link href="/listadesejos">Lista de Desejos</Link>
        </li>
        <li className={active === "reviewlivro" ? classActive : classInactive}>
          <Link href="/reviewlivro">Review de Livros</Link>
        </li>
      </ul>
      <div className="flex items-center h-full">
        <div className="w-10 h-10 rounded-full overflow-hidden">
          <img src="https://i.pravatar.cc/300" alt="avatar usuário" />
        </div>
      </div>
    </nav>
  )
}
