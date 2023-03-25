import { useState } from 'react'
import './App.css'
import EmployeeComponent from './components/EmployeeComponent'

function App() {
	const [count, setCount] = useState(0)

	return (
		<div className="App">
			<EmployeeComponent  />
		</div>
	)
}

export default App
