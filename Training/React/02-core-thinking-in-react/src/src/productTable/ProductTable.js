import { Table } from "antd";
import './productTable.css';

export default function ProductTable({ products, filterText, inStockOnly }) {
  const filtered = [];

  products.forEach((product) => {
       if (filterText !== '' && !product.name.toLowerCase().includes(filterText.toLowerCase())) return;

       if (inStockOnly && !product.stocked) return;

       filtered.push(product)
     }
  )

  const columns = [
    {
      title: 'Name',
      key: 'name',
      dataIndex: 'name',
      defaultSortOrder: 'ascending',
      sorter: (a, b) => b.name.toLowerCase().localeCompare(a.name.toLowerCase())
    },
    {
      title: 'Price',
      key: 'price',
      dataIndex: 'price',
      sorter: (a, b) => a.price - b.price,
    },
    {
      title: 'Category',
      key: 'category',
      dataIndex: 'category'
    },
    {
      title: 'Stocked',
      key: 'stocked',
      render: (text, record) => (`${record.stocked}`)
    }
  ];

  return (
     <Table
        className='product-table'
        columns={columns}
        dataSource={filtered}
        showSorterTooltip={{
          target: 'sorter-icon',
        }}
     />
  )
}