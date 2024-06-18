import './SearchBar.css'
import {Checkbox, Col, Row} from "antd";
import Search from "antd/es/input/Search";

export default function SearchBar() {
    return (
        <Row align="middle">
            <Col span={12}>
                <Search placeholder="input search text" className='search-bar' allowClear />
            </Col>
            <Col offset={1} span={11} className='check-box'>
                <Checkbox>Only show products in stock</Checkbox>
            </Col>
        </Row>
    )
}