import axios from 'axios'

class EmployeeService {
	private readonly API_URL = 'http://localhost:9191/api/v1/employee'
	private readonly EMPLOYEE_ID = 1

	getEmployee(employeeId: number) {
		return axios.get(`${this.API_URL}/${employeeId || this.EMPLOYEE_ID}`)
	}
}

export default new EmployeeService()
