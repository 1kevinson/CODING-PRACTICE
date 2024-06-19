import {Table} from "antd";
import './productTable.css'

export default function ProductTable({products}) {

    const columns = [
        {
            title: 'Name',
            dataIndex: 'name',
            defaultSortOrder: 'ascending',
            sorter: (a, b) => b.name.toLowerCase().localeCompare(a.name.toLowerCase())
        },
        {
            title: 'Price',
            dataIndex: 'price',
            sorter: (a, b) => a.price - b.price,
        },
        {
            title: 'Category',
            dataIndex: 'category'
        },
        {
            title: 'Stocked',
            render: (text, record) => (`${record.stocked}`)
        }
    ];

    return (
        <Table
            className='product-table'
            columns={columns}
            dataSource={products}
            showSorterTooltip={{
                target: 'sorter-icon',
            }}
        />
    )
}