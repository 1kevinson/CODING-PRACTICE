import './SearchBar.css'
import { Checkbox, Col, Row } from "antd";
import Search from "antd/es/input/Search";

export default function SearchBar({
  filterText,
  inStockOnly,
  onFilterTextChange,
  onInStockOnlyChange
}) {
    return (
        <Row align="middle">
            <Col span={12}>
                <Search
                    placeholder="input search text"
                    className='search-bar'
                    value={filterText}
                    onChange={(e) => onFilterTextChange(e.target.value)}
                    allowClear
                />
            </Col>
            <Col offset={1} span={11} className='check-box'>
                <Checkbox
                    checked={inStockOnly}
                    onChange={(e) => onInStockOnlyChange(e.target.checked)}
                >
                    Only show products in stock
                </Checkbox>
            </Col>
        </Row>
    )
}