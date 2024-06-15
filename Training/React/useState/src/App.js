import './App.css';
import {useState} from "react";
import {Button} from "antd";

export default function App() {
    const [count, setCount] = useState(0)

    function handleClick() {
        setCount(count + 1)
    }

    return (
        <div>
            <h2>Counter that update separately</h2>
            <MyButton handleclick={handleClick} count={count} />
            <MyButton handleclick={handleClick} count={count} />
        </div>
    );
}

function MyButton({ ...props }) {

    return (
        <Button type={"primary"} onClick={props.handleclick} className="blue-button">
            Clicked {props.count} times
        </Button>
    );
}