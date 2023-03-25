import { Component } from "react";
import employeeService from "../service/employeeService";

type EmployeeState = {
  employee: any;
  department: any;
  organization: any;
  search: number;
};

export default class EmployeeComponent extends Component {
  constructor(props: any) {
    super(props);
  }

  state: EmployeeState = {
    employee: {},
    department: {},
    organization: {},
    search: NaN,
  };

  updateEmployee = () => {
    employeeService
      .getEmployee(this.state.search || 1)
      .then((response: any) => {
        this.setState({
          employee: response.data.employee,
          department: response.data.department,
          organization: response.data.organization,
        });
      });
  };

  componentDidMount(): void {
    employeeService.getEmployee(1).then((response: any) => {
      this.setState({
        employee: response.data.employee,
        department: response.data.department,
        organization: response.data.organization,
      });
    });
  }

  render() {
    return (
      <div className="container row" style={{ width: window.innerWidth * 0.6 }}>
        <div className="col"></div>
        <div className="col-x-12">
          <div className="col-md-12">
            <div className="input-group mb-3">
              <input
                value={this.state.search}
                type="number"
                onChange={(element) =>
                  this.setState({ search: element.target.value })
                }
                className="form-control"
                placeholder="Search for employee's details..."
                aria-label="Search for..."
                aria-describedby="button-addon2"
              />
              <button
                onClick={this.updateEmployee}
                className="btn btn-outline-secondary"
                type="button"
                id="button-addon2"
              >
                Button
              </button>
            </div>
          </div>
          <div className="card mb-5">
            <h3 className="text-center card-header">Employee Details</h3>
            <div className="card-body">
              <div className="row">
                <p className="text-justify">
                  <strong>Employee First Name: </strong>{" "}
                  {this.state.employee.firstName}
                </p>
              </div>
              <div className="row">
                <p className="text-justify">
                  <strong>Employee Last Name: </strong>{" "}
                  {this.state.employee.lastName}
                </p>
              </div>
              <div className="row">
                <p className="text-justify">
                  <strong>Employee Email Name: </strong>{" "}
                  {this.state.employee.email}
                </p>
              </div>
            </div>
            <h3 className="text-center card-header">Department Details</h3>
            <div className="card-body">
              <div className="row">
                <p className="text-justify">
                  <strong>Department Name: </strong>{" "}
                  {this.state.department.departmentName}
                </p>
              </div>
              <div className="row">
                <p className="text-justify">
                  <strong>Department Description: </strong>{" "}
                  {this.state.department.departmentDescription}
                </p>
              </div>
              <div className="row">
                <p className="text-justify">
                  <strong>Department Code: </strong>{" "}
                  {this.state.department.departmentCode}
                </p>
              </div>
            </div>
            <h3 className="text-center card-header">Organization Details</h3>
            <div className="card-body">
              <div className="row">
                <p className="text-justify">
                  <strong>Organization Name: </strong>{" "}
                  {this.state.organization.organizationName}
                </p>
              </div>
              <div className="row">
                <p className="text-justify">
                  <strong>Organization Description: </strong>{" "}
                  {this.state.organization.organizationDescription}
                </p>
              </div>
              <div className="row">
                <p className="text-justify">
                  <strong>Organization Code: </strong>{" "}
                  {this.state.organization.organizationCode}
                </p>
              </div>
              <div className="row">
                <p className="text-justify">
                  <strong>Organization Creation Date: </strong>{" "}
                  {this.state.organization.creationDate}
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
