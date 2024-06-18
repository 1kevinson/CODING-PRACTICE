import {Table} from "antd";

export default function ProductTable({products}) {

    const columns = [
        {
            title: 'Name',
            dataIndex: 'name',
            defaultSortOrder: 'ascend',
            sorter: (a, b) => a.name.length - b.name.length
        },
        {
            title: 'Price',
            dataIndex: 'price',
            defaultSortOrder: 'ascend',
            sorter: (a, b) => a.age - b.age,
        },
        {
            title: 'Category',
            dataIndex: 'category'
        },
        {
            title: 'Stocked',
            dataIndex: 'stocked'
        }
    ];

    return (
        <Table
            columns={columns}
            dataSource={products}
            showSorterTooltip={{
                target: 'sorter-icon',
            }}
        />
    )
}