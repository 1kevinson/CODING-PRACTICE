import './FilterableProductTable.css'
import SearchBar from "../searchBar/SearchBar";
import ProductTable from "../productTable/ProductTable";
import { useState } from "react";

export default function FilterableProductTable({ products }) {
    const [filterText, setFilterText] = useState('');
    const [inStockOnly, setInStockOnly] = useState(false);

    return (
        <div>
            <SearchBar
                filterText={filterText}
                inStockOnly={inStockOnly} />
            <ProductTable
                filterText={filterText}
                inStockOnly={inStockOnly}
                products={products} />
        </div>
    )
}