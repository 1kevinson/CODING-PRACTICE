import './App.css';
import {Col, Row} from "antd";
import FilterableProductTable from "../src/filterableProductTable/FilterableProductTable";
import {useState, useEffect} from "react";


function App() {
    const [data, setData] = useState([]);

    useEffect(() => {
        fetch(process.env.PUBLIC_URL + '/data.json')
            .then(response => response.json())
            .then(jsonData => setData(jsonData))
            .catch((error) => console.error('Error reading JSON file →', error));
    }, []);

    return (
        <div className='container'>
            <Row justify='center' className='title--zone'>
                <Col span={8} className='title--work-zone'>
                    Thinking in React
                </Col>
            </Row>
            <Row className='work-zone'>
                <Col span={24}>
                    <FilterableProductTable products={data} />
                </Col>
            </Row>
        </div>
    );
}

export default App;
