'use client'

import {
    Dropdown, 
    DropdownTrigger, 
    DropdownMenu, 
    DropdownItem, 
    Button
} from "@nextui-org/react";
import { ChevronDown } from "lucide-react";

export default function DropdownActions() {
  return (
    <Dropdown >
      <DropdownTrigger>
        <Button 
          variant="light" 
        >
          <ChevronDown/>
        </Button>
      </DropdownTrigger>
      <DropdownMenu aria-label="Static Actions">
        <DropdownItem key="new">Adicionar Lista de Desejos</DropdownItem>
        <DropdownItem key="new">Adicionar um Review</DropdownItem>
      </DropdownMenu>
    </Dropdown>
  );
}
