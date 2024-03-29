package com.example.qlkhachsan.Repository;

import com.example.qlkhachsan.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Query(value = "SELECT * from employee", nativeQuery = true)
    List<Employee> showListEmployee();

    @Modifying
    @Query(value = "INSERT INTO employee (employee_id,employee_name,birth,gender,address,email,phone_number,salary) " +
            "VALUES (:employee_id,:employee_name,:birth,:gender,:address,:email,:phone_number,:salary)",nativeQuery = true)
    void addEmployee(@Param("employee_id") Long employeeId, @Param("employee_name") String employeeName,
                     @Param("birth") String birth, @Param("gender") String gender, @Param("address") String address,
                     @Param("email") String email, @Param("phone_number") String phoneNumber, @Param("salary") Double salary);

    @Modifying
    @Query(value = "SELECT * from employee WHERE employee_id = :employee_id",nativeQuery = true)
    List<Employee> findEmployeeByID(@Param("employee_id") Long employeeId);

    @Modifying
    @Query(value = "UPDATE employee e SET e.employee_name=:employee_name, e.birth=:birth, e.gender=:gender, " +
            "e.address=:address, e.email=:email, e.phone_number=:phone_number, e.salary=:salary " +
            "WHERE e.employee_id = :employee_id",nativeQuery = true)
    void editEmployee(@Param("employee_id") Long employeeId,@Param("employee_name") String employeeName,
                      @Param("birth") String birth, @Param("gender") String gender, @Param("address") String address,
                      @Param("email") String email, @Param("phone_number") String phoneNumber, @Param("salary") Double salary);

    @Modifying
    @Query(value = "DELETE FROM employee WHERE employee_id = :employee_id",nativeQuery = true)
    void deleteEmployee(@Param("employee_id") Long employeeId);
}