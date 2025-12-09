package com.company;

import java.sql.*;

public class CompanyDatabase {
    public static void listAllEmployees(DatabaseConnection conn) throws SQLException {
        String query = "SELECT * FROM Employees";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Employees:");
            while (rs.next()) {
                System.out.printf("ID: %d, Name: %s %s, Position: %s, Department ID: %d%n",
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("position"),
                        rs.getInt("department_id"));
            }
        }
    }

    public static void listAllTasks(DatabaseConnection conn) throws SQLException {
        String query = "SELECT * FROM Tasks";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Tasks:");
            while (rs.next()) {
                System.out.printf("ID: %d, Description: %s, Employee ID: %d%n",
                        rs.getInt("id"),
                        rs.getString("description"),
                        rs.getInt("employee_id"));
            }
        }
    }

    public static void listEmployeesByDepartment(DatabaseConnection conn, int departmentId) throws SQLException {
        String query = "SELECT * FROM Employees WHERE department_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, departmentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("Employees in department " + departmentId + ":");
                while (rs.next()) {
                    System.out.printf("ID: %d, Name: %s %s, Position: %s%n",
                            rs.getInt("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("position"));
                }
            }
        }
    }

    public static void addTaskForEmployee(DatabaseConnection conn, int employeeId, String taskDescription) throws SQLException {
        String query = "INSERT INTO Tasks (description, employee_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, taskDescription);
            pstmt.setInt(2, employeeId);
            pstmt.executeUpdate();
            System.out.println("Task added successfully.");
        }
    }

    public static void listTasksForEmployee(DatabaseConnection conn, int employeeId) throws SQLException {
        String query = "SELECT * FROM Tasks WHERE employee_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, employeeId);
            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("Tasks for employee " + employeeId + ":");
                while (rs.next()) {
                    System.out.printf("ID: %d, Description: %s%n",
                            rs.getInt("id"),
                            rs.getString("description"));
                }
            }
        }
    }

    public static void deleteEmployee(DatabaseConnection conn, int employeeId) throws SQLException {
        String query = "DELETE FROM Employees WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, employeeId);
            pstmt.executeUpdate();
            System.out.println("Employee deleted successfully.");
        }

        String query2 = "DELETE FROM Tasks WHERE employee_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query2)) {
            pstmt.setInt(1, employeeId);
            pstmt.executeUpdate();
            System.out.println("Employee tasks deleted successfully.");
        }
    }
}