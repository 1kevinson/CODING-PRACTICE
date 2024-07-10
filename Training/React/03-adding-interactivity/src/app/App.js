import './App.css';
import {Col, Row} from "antd";

function App() {
    return (
        <div className='container'>
            <Row justify='center' className='title--zone'>
                <Col span={8} className='title--work-zone'>
                    Adding Interactivity
                </Col>
            </Row>
            <Row className='work-zone'>
                <Col span={24}></Col>
            </Row>
        </div>
    );
}

export default App;
