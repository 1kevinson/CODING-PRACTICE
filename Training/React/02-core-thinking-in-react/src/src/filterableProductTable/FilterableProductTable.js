import './FilterableProductTable.css'
import SearchBar from "../searchBar/SearchBar";
import ProductTable from "../productTable/ProductTable";

export default function FilterableProductTable({products}) {
    return (
        <div>
            <SearchBar/>
            <ProductTable className='product-table' products={products}/>
        </div>
    )
}